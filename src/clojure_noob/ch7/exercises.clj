(ns clojure-noob.ch7.exercises)

(eval (read-string "(println \"Charles, \" \"Serenity\")"))

(defn parse-expression
  [expr]
  (cond
    (empty? expr) expr
    (not (seq? expr)) expr
    (= (count expr) 1) (first expr)
    (some #{'+} expr)
    (break-tree-at-symbol-and-parse expr '+ parse-expression)
    (some #{'-} expr)
    (break-tree-at-symbol-and-parse expr '- parse-expression)
    (some #{'*} expr)
    (break-tree-at-symbol-and-parse expr '* parse-expression)))

(defn break-tree-at-symbol-and-parse
  [expr s f]
  (list
   s
   (f (take-while (partial not= s) expr))
   (f (rest (drop-while (partial not= s) expr)))))

(let [ls '(1 + 3 * 4 - 5)]
  (parse-expression ls))
