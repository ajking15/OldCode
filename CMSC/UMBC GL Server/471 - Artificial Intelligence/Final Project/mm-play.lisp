(if (not (find-package :mm-cmrd)) (make-package :mm-cmrd))

(in-package :mm-cmrd)

(use-package :mm)

(defvar *model*  '()
  "List of colors for the current game")

(defun init-guess (&optional (colors *colors*) (code-length *code-length*)
			     &aux reds whites)

  (let ((guess1 (loop for i from 1 to code-length collect (car colors))))
    (loop while guess1
	       do (format t "Guessing ~s...~% " guess1)
	       (multiple-value-setq (reds whites) (mm-score guess1))

	       (if (equalp reds code-length) 
		   (progn (format t "Guessed ~s correctly in ~s guesses!~%"
			   guess1 *guesses*)
			  (setf *model* guess1)
			  (return-from init-guess)))
	       (loop for i from 1 to reds
		     do (setf *model* (append *model* 
					      (list (car colors))))
		     )
    	       (if (or (not (equalp code-length (length *model*))) (null *model*))
		   (progn
		     (init-guess (cdr colors) code-length)
		     (setf guess1 '())) (progn (setf guess1 '()) (return-from init-guess)) )))
)
