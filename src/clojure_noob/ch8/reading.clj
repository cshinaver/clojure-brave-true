(ns clojure-noob.ch8.reading)

;; (macroexpand
;;  '(when (partial = true true)
;;     (+ 1 2)))

(defmacro my-print
  [expression]
  (list 'let ['result expression]
        (list 'println 'result)
        'result))

; reader macro for quoting is '
'(+ 1 2)

; Syntax quoting vs regular quoting
'+ ; regular quoting, does not include a namespace
`+ ; syntax quoting, includes symbol's full namespace

; quoting and syntax quoting both recursively quote
'(+ 1 2)
`(+ 1 2)

; syntax quoting includes namespaces to avoid name collisions
; ~ can be used with syntax quoting to unquote forms
`(+ 1 (inc 1))
`(+ 1 ~(inc 1))

(defmacro long-code-critic
  "Phrases are courtesy of Hermes Conrad from Futurama"
  [bad good]
  (list 'do
        (list 'println
              "Great squid of Madrid, this is bad code:"
              (list 'quote bad))
        (list 'println
              "Sweet gorilla of Manila, this is good code:"
              (list 'quote good))))


(defmacro code-critic
  [bad good]
  `(do
     (println
      "Great squid of Madrid, this is bad code: "
      (quote ~bad))
     (println
      "Sweet gorilla of Manilla, this is good code: "
      (quote ~good))))


(defmacro derpin
  []
  `(do
     (println "herpin")
     (println "derpin")))

(defn criticize-code
  [criticism code]
  `(println ~criticism (quote ~code)))

(defmacro code-critic
  [bad good]
  `(do ~(criticize-code "Cursed bacteria of Liberia, this is bad code: " bad)
       ~(criticize-code "Sweet sacred boa of Western and Eastern Samoa, this is good code: " good)))

(defmacro code-critic
  [bad good]
  `(do ~@(map #(apply criticize-code %)
             [["Great squid of Madrid, this is bad code: " bad]
              ["Sweet gorilla of Manila, this is good code: " good]])))

; message binding shadows
(def message "Good job!")
(defmacro with-mischief
  [& stuff-to-do]
  (concat (list 'let ['message "Oh, big deal!"])
          stuff-to-do))

;; (with-mischief
;;   (println "Here's how I feel about that thing you did: " message))

; syntax quoting does not allow variable capture
(defmacro with-mischief-syntax-quoting
  [& stuff-to-do]
  `(let [message "Oh, big deal!"]
     ~@stuff-to-do))

;; (with-mischief-syntax-quoting
;;   (println "Here's how I feel about that thing you did: " message))

; in order to avoid variable capture, you can create a new unique symbol
; and bind to that
(defmacro without-mischief
  [& stuff-to-do]
  (let [macro-message (gensym 'message)]
    `(let [~macro-message "Oh, big deal!"]
       ~@stuff-to-do
       (println "I still need to say: " ~macro-message))))

;; (without-mischief
;;  (println "Here's how I feel about that thing you did: " message))

; Auto gensym
;; `(let [name# "Larry Potter"] name#)

; Double evaluation
; happens when form passed as argument to macro and is evald more than once
(defmacro report
  [to-try]
  `(if ~to-try
     (println (quote ~to-try) "was successful: " ~to-try)
     (println (quote ~to-try) "was not successful: " ~to-try)))

;; (report (do (Thread/sleep 1000) (+ 1 1)))

(defmacro report-single-eval
  [to-try]
  `(let [result# ~to-try]
     (if result#
       (println (quote ~to-try) "was successful: " result#)
       (println (quote ~to-try) "was not successful: " result#))))

;; (report-single-eval (do (Thread/sleep 1000) (+ 1 1)))

;; (doseq [code [(= 1 1) (= 1 2)]]
;;     (report code))

; Validation functions
(def order-details
  {:name "Michard Blimmons"
   :email "mitchard.blimmonsgmail.com"})

(def order-details-validations
  {:name
   ["Please enter a name" not-empty]

   :email
   ["Please enter an email address" not-empty

    "Your email address doesn't look like an email address"
    #(or (empty? %) (re-seq #"@" %))]})

(defn error-messages-for
  "Return a seq of error messages"
  [to-validate message-validator-pairs]
  (map first (filter #(not ((second %) to-validate))
                     (partition 2 message-validator-pairs))))

(defn validate
  "Returns a map with a vector of errors for each key"
  [to-validate validations]
  (reduce (fn [errors validation]
            (let [[fieldname validation-check-groups] validation
                  value (get to-validate fieldname)
                  error-messages (error-messages-for value validation-check-groups)]
              (if (empty? error-messages)
                errors
                (assoc errors fieldname error-messages))))
          {}
          validations))

(defmacro if-valid
  [record validations success-code failure-code]
  `(let [errors# (validate ~record ~validations)]
     (if (empty? errors#)
       ~success-code
       ~failure-code)))
                                 
;; (if-valid
;;  order-details order-details-validations
;;  (println "success")
;;  (println "failure"))
