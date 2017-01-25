(ns clojure-noob.core
  (:gen-class))

(defn x-chop
  "Test multi arity fuction"
  ([name chop]
   (println (str "I " chop " chop " name "!")))
  ([name] (x-chop name "karate")))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (x-chop "kanye" "ninja")
  (x-chop "jim"))
