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

; Clojure symbol resolution
; Special form, local binding, namespace mapping, or exception
(if true :a :b)
(let [x 5]
  (+ x 3))
(def x 5)

(defmacro ignore-last-operand
  [function-call]
  (butlast function-call))

(ignore-last-operand (+ 1 2 (println "derpin")))

(defmacro infix
  [infix-list]
  (list (second infix-list) (first infix-list) (last infix-list)))

(macroexpand '(infix (1 + 2)))

(defn read-resource
  "Read a resource into a string"
  [path]
  (-> path
      clojure.java.io/resource
      slurp
      read-string))

(read-resource "suspects.csv")
