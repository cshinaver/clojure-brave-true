(ns clojure-noob.ch7.reading)

; Reorders source code and evals returned form
(defmacro backwards
  [form]
  (reverse form))

(backwards (" backwards" " am" "I" str))

; Reader takes strings and translates into data structures
(read-string "(+ 1 2)")

; Evaluator evals clojure data structures as code
(eval (list + 1 2 3))

; Macros go inbetween reader and evaluator
; (-> input read-string macro eval)

; Reader form: textual representation of data structures
; "(+ 1 2)"

(read-string "#(+ 1 %)")

; Reader macros affect behavior of reader (text -> data structures)
; macro characters: ', #, @
(read-string "'(a a b c)")
(read-string "@var")
(read-string "; ignore!\n(+ 1 2)")

