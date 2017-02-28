(ns clojure-noob.ch8.exercises
  (:require
   [clojure-noob.ch8.reading
    :refer [order-details order-details-validations validate]]))

(when (= 1 1)
  (+ 1 2))

(defmacro when-valid
  [record validations & success-code]
  `(let [errors# (validate ~record ~validations)]
     (if (empty? errors#)
       (do
         ~@success-code))))


(when-valid
    (assoc order-details :email "herp@derp.com")
    order-details-validations
    (println "wewt")
    (println "wewt"))


(defmacro my-or
  ([x] x)
  ([x & n]
   `(let [result# ~x]
      (if result#
        result#
        (my-or ~@n)))))


(defmacro defattrs
  [& args]
  `(doall
    ~(map (fn [[n attr]] `(def ~n (comp ~attr :attributes)))
          (partition 2 args))))

(defattrs
   c-int :intelligence
   c-str :strength
   c-dex :dexterity)


