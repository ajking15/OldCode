;-----------------------------------------
;;Final Project, CMSC, Fall 2009
;;-----------------------------------------
;;(if (not (find-package :mm-cmrd)) (make-package :mm-cmrd))
;;(in-package :mm-cmrd)
;;(use-package :mm)

(defvar *model*  '()
  "List of colors for the current game")

(defvar *bias1Colors* '(V I W B R G O Y))
(defvar *bias1data* '(213/808 1/808 1/808 185/808 1/4 1/808 1/808 51/202))

;;Initial guesses of each color to reduce the search space
(defun init-guess (&optional (colors *colors*) 
			     (code-length *code-length*)
			     &aux reds whites)

  (setf colors (remove-duplicates colors))
  (let ((guess 
	 (loop for i from 1 to code-length collect (car colors))))
    (loop while guess
;	  do (format t "Guessing ~s...~% " guess)
	  do(multiple-value-setq (reds whites) 
				 (mm-score guess *code* colors))
	  
	  (if (equalp reds code-length) 
	      (progn ;(format t "Guessed ~s correctly in ~s guesses!~%"
;			     guess *guesses*)
		     (return-from init-guess)))
	  (loop for i from 1 to reds
		do (setf *model* (append *model* 
					 (list (car colors)))))
	  (if (or (not (equalp code-length (length *model*))) 
		  (null *model*))
	      (progn
		(init-guess (cdr colors) code-length)
		(setf guess '())) 
	    (progn (setf guess '()) (return-from init-guess))))))


;;;;train1List
;(defun isMemberWithCount (color list)
;  (if (member color list) (+ 1 (nth (


;;;;;;;;;;;;;;;;;;;;;;Global Variable
(setf colorCnt '0)
(setf train1List '(0 0 0 0 0 0 0 0))
(setf positiveGuess '())

;;;;;;;;;;;;;;;;;;;;
;;Called if trainBias1
(defun trainBias1 ()
  (setf file 'testbias1.txt)
  (setf newColors (copy-list *bias1Colors*))
  (setf i 0)
  (loop for i from 0 to 7 do
	(has-color file (nth i newColors) i))
  (loop for k from 0 to 7 do
	(print k)
	(setf (nth k train1List) (+ (nth k train1List) 1))
	(setf colorCnt (+ 1 colorCnt)))
  (loop for j from 0 to 7 do
	(setf (nth j train1List) (/ (nth j train1List) colorCnt))	
	(print j)))


(defun has-color (file newColor pos)
  (with-open-file
   (str file :direction :input
	:if-does-not-exist :error)
  ;;Add 8 to color count
   (loop for i from 0 to 0 do
	 (setf learnGuess (read-token-line str))
	 (if (equalp (car learnGuess) '+)
	     (progn
	       (setf colorCnt (+ 1 colorCnt))
	       (setf posGuess (copy-list (cdr learnGuess)))
	       (setf currColorCnt (nth pos train1List))
	       (print newColor)
	       (print (count newColor posGuess))
	       (setf currColorCnt (+ currColorCnt (count newColor posGuess)))
	       (setf (nth pos train1List) currColorCnt)
)))))
   
;	 (if (not (null (member color posGuess))) 
;       (+ 1


;;Function to solve the mastermind puzzle
(defun mm-player (&optional (colors *colors*) (code-length *code-length*)
			    (generator 'mm-gen-random))
  "Implementing a variation to solve the 
   Mastermind Game"
  (setf start (get-internal-run-time))
  ;set our model. narrow our guess to a list of 
  ;all the colors and their quantity

  (if (equalp generator 'test-bias1-pos)
      ;(loop for i from 0 to 7 do
	;(setf colors *bias1Colors*))    
      (progn 
	;(print "HI")
	;(print colors)
	;(setf colors *bias1Colors*)
	;(print colors)
	(bias1-sort *bias1data* colors)))
	;(setf *code* '(R R B R B B R Y))))
  ;(setf *code* '(R R B R B B R Y))
  

  (set-up colors code-length)
  
  (loop for iCounter from 0 to 2000000 do
	(progn
	  (cond((equalp reds code-length)
;		(format t "MM-player guessed ~s correctly in ~s guesses!~%" 
;			guess *guesses*)
		(progn
		  (setf mid (get-internal-run-time))
		  (setf stop (- mid start))
		  (return-from mm-player)))
	       (t (progn 
		    (multiple-value-setq (guess reds)
					 (choose-best-guess guess reds colors)
)))))))


(defun bias1-sort (percents colors-list)
    (loop for i from (- (length colors-list) 1) downto 0 do ()
	  (loop for j from 0 to i do ()
		(if (> (nth i percents) (nth j percents))
		    (progn
		      (rotatef (nth i percents) (nth j percents))
		      (rotatef (nth i colors-list) (nth j colors-list))))))
    colors-list)


;;Function that helps setup the game
(defun set-up (colors code-length) 
 (setf *model* '())
  (setf *ranking* '())
  (setf *guesses* 0)
  (loop for i from 1 to code-length do
	(setf *ranking* (append (list '0) *ranking*)))
  (init-guess colors code-length)
  (setf guess *model*)
  (multiple-value-setq (reds whites)
		       (mm-score guess *code* colors))
)


;;Chooses the best guess for the game
(defun choose-best-guess (guess reds colors)
  "solves mastermind"
  (setf tempGuess (copy-list guess))
  (setf tGuess (copy-list guess))
  (setf xy 1)
  (setf innerLoop (length guess))
  (setf loopLength (- (length guess) 1))

  (loop for xyz from 0 to (length guess) do
	    (loop for zyx from xy to loopLength do
		  ;;if the positions are different check score  
		  (setf tempGuess (copy-list guess))
		  (setf tempGuess (swap xyz zyx tempGuess))
		  ;; checks whether or not method has been locked in place
		  (if (and (not (equalp (nth xyz *ranking*) '2))
			   (not (equalp (nth zyx *ranking*) '2))
			   (not (equalp (nth xyz tempGuess)
					(nth zyx tempGuess))))
		      (progn
;			(format t "Guessing ~s...~%" tempGuess)   
			(multiple-value-setq (tRed tWhite)
					     (mm-score tempGuess *code* colors))
			
			(if (equalp tRed (+ reds 2))
			    (progn
			      ;; swap just placed two pegs into the right
			      ;; position. save so they're locked
			      (setf (nth xyz *ranking*) '2)
			      (setf (nth zyx *ranking*) '2)
			      (setf reds tRed)
			      (setf guess (copy-list tempGuess))
			      (return)))
			
			(if (> tRed reds)
			    (progn
			      (setf reds tRed)
			      (setf guess (copy-list tempGuess)))))))
		  
	      (incf xy))
      (values guess reds)
)


#|
(defun swap (pos1 pos2)
  "Swaps two positions in a list"
  (setf tempColor (nth pos1 tempGuess))  
  (setf (nth pos1 tempGuess) (nth pos2 tempGuess))  
  (setf (nth pos2 tempGuess) tempColor)
  (setf tempGuess tempGuess)
)
|#



;;Swaps two positions in a list
(defun swap (pos1 pos2 swapGuess)
  "Swaps two positions in a list"
  (rotatef (nth pos1 swapGuess) (nth pos2 swapGuess))
  ;;(setf tempColor (nth pos1 swapGuess))  
  ;;(setf (nth pos1 swapGuess) (nth pos2 swapGuess))  
  ;;(setf (nth pos2 swapGuess) tempColor)
  (setf swapGuess swapGuess)
)


;;Used from desJardins rushHour project
;; Read a line of tokens from an input stream
;(defun read-token-line (str &aux line (next t) (start 0)
;			    (tokens nil))
;  "Read a line of tokens into a list"
  ;; First input the line into a string
;  (setf line (read-line str))
;  (loop while (not (eq next :done))
;    do (progn
;	 (multiple-value-setq
;	     (next start)
;	     ;; Loop, reading tokens sequentially from the string
;	   (read-from-string line nil :done :start start))
;	 (if (not (eq next :done))
;	     (setf tokens (nconc tokens (list next))))))
;  tokens)