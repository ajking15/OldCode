#|
(if (not (find-package :mm)) (make-package :mm))
(in-package :mm)

(export '(tournament mmround presults player
		     tournament-name tournament-nRounds 
		     tournament-gen-fn tournament-rounds
		     mmround-index mmround-code mmround-results
		     presults-player presults-guesses presults-starttime
		     presults-endtime presults-runtime
		     player-name player-guess-fn
		     *colors* *code-length* *code* *results* *guesses*
		     mm-tournament mm-player-init mm-player-cleanup
		     mm-gen-random mm-score symless check
		     is-player-results is-player tourn-player-results))

|#

;;=======================================================================
;; File: mm.lisp
;; Infrastructure for Mastermind course project, CMSC 471, Fall 2009
;; (c) Marie desJardins, October 2009
;; VERSION 2.0:  LAST UPDATED 11/2/09
;;
;; Class definitions:  tournament [results], mmround [results], 
;;   presults (player results) , player
;; Global variables: *colors* (list of colors (symbols)), *code-length*,
;;   *results* (used to store tournament results), *guesses* (used
;;   to keep track of # guesses for the current player)
;;
;; Main tournament functions:
;;   - mm-tournament() - set up and run a tournament with a list of players
;;   - mm-player-init() - initialize a single player within a tournament 
;;     round
;;   - mm-player-cleanup() - finalize a player's results after they've 
;;     played one round
;;
;; Code generation and scoring functions:
;;   - mm-gen-random() - generate a random code
;;   - mm-score() - score a guess against the current code (*code*)
;;
;; Utility functions
;;   - symless() - compare two symbols lexicographically
;;   - check() - check to see whether a guess is correct (not used)
;;   - is-player-results() - return T if a presult instance has the
;;     given player name
;;   - is-player() - return T if a player instance has the given name
;;   - tournament-player-results() - returns results for a single
;;     player in the current round of a tournament


;=======================================================================
;; Class definitions

;; Used to store information associated with a multi-round
;; mastermind tournament:  a unique (usually gensym'ed) name,
;; the # of rounds, a code chooser function, and a list of "mmround"
;; instances, one for each round of play
(defclass tournament ()
  ((name :accessor tournament-name :initarg :name)
   (nRounds :accessor tournament-nRounds :initarg :nRounds)
   (gen-fn :accessor tournament-gen-fn :initarg :gen-fn)
   (rounds :accessor tournament-rounds :initarg :rounds)))

;; Stores information associated with a single round of a
;; tournament:  the index (game #), code being guessed, and
;; a list of results (one for each player in the tournament)
(defclass mmround ()
  ((index :accessor mmround-index :initarg :index)
   (code :accessor mmround-code :initarg :code)
   (results :accessor mmround-results :initarg :results)))
  
;; Stores results for a single player in a single round
;; of mastermind:  player name, # guesses taken, start time,
;; end time, and total run time (end - start)
(defclass presults ()
  ((player :accessor presults-player :initarg :player)
   (guesses :accessor presults-guesses :initarg :guesses)
   (starttime :accessor presults-starttime :initarg :starttime)
   (endtime :accessor presults-endtime :initarg :endtime)
   (runtime :accessor presults-runtime :initarg :runtime)))

;; Stores information associated with a single player:
;; the player name (a symbol), and the function that implements
;; that player's guessing strategy
(defclass player ()
  ((name :accessor player-name :initarg :name)
   (guess-fn :accessor player-guess-fn :initarg :guess-fn)))


;;=======================================================================
;; Global variables

(defvar *colors* '(b i v w g y o r)

;;'(m1 m2 m3 m4 m5 m6 m7 m8);; m9 m10 m11 m12 m13 m14 m15 m16
		    ;;  m17 m18 m19 m20 m21 m22 m23 m24 m25 m26);; m27 m28 m29
  ;;	m30 m31 m32 m33 m34 m35 m36 m37 m38 m39 m40 m41 m42
  ;;		m43 m44)
  "List of colors for the current game")

(defvar *code-length* 8
  "Length of code to be guessed in the current game")

(defvar *code* nil
  "Used to store the correct code -- not to be used in guessing!!
If you are found to be using this global variable anywhere in 
your guesser's code, your code will receive a zero.")

(defvar *results* nil
  "Used to store results for current tournament (a Tournament instance)")

(defvar *guesses* 0
  "Number of guesses so far for current player")


;;=======================================================================
;; Main programs for setting up and running a mastermind tournament

;; TO RUN A TOURNAMENT (which can have one or more rounds and
;; one or more players): 
;;   (mm-tournament :colors <colors>     ;; e.g. '(red blue yellow)
;;                  :code-length <code-length>    ;; e.g. 4
;;                  :gen-fn <function name>       ;; e.g. 'mm-gen-random
;;                  :player <player instance list>   
;;                                        ;; e.g. (list mm-mdj::*mm-gentest-player*)
;;                  :rounds <N>           ;; e.g. 1

(defun mm-tournament (&key (colors *colors*) (code-length *code-length*)
			   (gen-fn 'mm-gen-random) players (rounds 1)
			   &aux next-results)
  "Initialize a mastermind tournament; players is a list of one or more 
player instances"
  ;; Create the *results* tournament instance
  (let ((name (gensym "T-")))
    (setf *results* (set name
			 (make-instance 'tournament
					:name name
					:nRounds rounds
					:gen-fn gen-fn
					:rounds nil))))

  ;; Play each round
  (loop for i from 1 to rounds 
	do
	(progn 
	  ;; Initialize the new set of results -- will always be
	  ;; the first entry in results throughout the tournament round
	  (push
	   (make-instance 'mmround
			  :index i
			  ;; Generate a code for the round
			  :code (funcall (symbol-function gen-fn)
					 colors code-length)
			  ;; Set up empty results structures
			  :results
			  (loop for p in players 
				collect (make-instance 
					 'presults
					 :player p)))
	   (tournament-rounds *results*))
	  ;; Let each player play
	  (loop for p in players do
		(mm-player-init p)
		(funcall (symbol-function (player-name p)) colors code-length)
		(mm-player-cleanup p)))))


(defun mm-player-init (player)
  "Initialize a single player within a round of mastermind tournament"
  (setf *guesses* 0)
  ;; Look for the player in this round's results
  (let ((player-results (tourn-player-results player *results*)))
    (cond ((not player-results)
	   (error "No such player ~s in playerinit" player))
	  ;; If found, initialize # guesses & starttime
	  (t (setf (presults-guesses player-results) 0)
	     (setf (presults-starttime player-results)
		   (get-internal-run-time))))))


(defun mm-player-cleanup (player)
  "Do bookkeeping after a  player's round ends within a mastermind tournament"
  ;; Look for the player in this round's results
  (let ((player-results (tourn-player-results player *results*)))
    (cond ((not player-results)
	   (error "No such player ~s in tournament" player))
	  ;; If found, update # guesses, endtime, and runtime
	  (t (setf (presults-guesses player-results) *guesses*)
	     (setf (presults-endtime player-results) 
		   (get-internal-run-time))
	     (setf (presults-runtime player-results)
		   (- (presults-endtime player-results) 
		      (presults-starttime player-results)))))))


;;=======================================================================
;; Functions for generating and scoring codes
;;

(defun mm-gen-random (&optional (colors *colors*) (code-length *code-length*))
  "Generates a random code and sets global variable *code* to new code."
  (setf *code*
	(loop for i from 1 to code-length
	      collect (nth (random 4) '(v w b i)))))

(defun mm-score (guess &optional (code *code*) (colors *colors*)
		       &aux reds whites g c)
  "Mastermind scoring function:  returns (values reds whites).
Error conditions return '(NIL NIL)"
  ;; Error checking
  ;; Don't crash if there's an error, but proceed at the player's risk...
  (when (not (eq (length guess) (length code)))
    (format *error-output* "Code ~s and guess ~s are not the same length~%"
	    code guess)
    (return-from mm-score (values nil nil)))
  (when (not (every #'(lambda (g) (member g colors))
		    guess))
    (format *error-output* "Guess ~s contains non-colors~%" guess)
    (return-from mm-score (values nil nil)))

  ;; Initialize counts & record this guess
  (setf reds 0) (setf whites 0)
  (incf *guesses*)

  ;; Compute reds by checking matching elements in lists
  (loop for gnext in guess
	for cnext in code
	do (if (eq gnext cnext) (incf reds)))

  ;; Compute total # matches by sorting both lists then counting
  ;; matches as lists are popped -- perhaps not the most efficient,
  ;; but the lists won't be so long that it matters.
  (setf g (sort (copy-list guess) #'symless))
  (setf c (sort (copy-list code) #'symless))
  (loop while (and g c)
	do
	(cond ((eq (car g) (car c))  ;; Got a match: one more white
	       (incf whites)
	       (pop g) (pop c))
	      ;; No match -- if color in guess is alphabetically
	      ;; before color in white, move to next color in guess;
	      ;; else move to next color in code
	      ((symless (car g) (car c))
	       (pop g))                 
	      (t (pop c))))

  ;; Double-counted all same-place matches, so subtract reds
  ;; from the total matches to get whites
  (decf whites reds)

  ;; Return two values: reds and whites
  (values reds whites))


;;=======================================================================
;; Utility functions

(defun symless (s1 s2)
  "Return T if the first symbol is alphabetically before the second"
  (string< (symbol-name s1) (symbol-name s2)))

(defun check (g &optional (c *code*))
  "Return T if guess G is the same as code C, else NIL"
  (loop for gnext in g
	for cnext in c
	always (eq gnext cnext)))


(defun is-player-results (pname presults)
  "Return t if PLAYER is a presults instance with :NAME = PNAME"
  (is-player pname (presults-player presults)))


(defun is-player (pname player)
  "Return t if PLAYER is a player instance with :NAME = PNAME"
  (eq pname (player-name player)))


(defun tourn-player-results (player results)
  "Return the results associated with a particular player in the
current round of a tournament"
  (find (player-name player)
	(mmround-results (car (tournament-rounds *results*)))
	:test #'is-player-results))