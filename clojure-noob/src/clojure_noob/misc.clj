(ns clojure-noob.misc)

(defn x-chop
  "Test multi arity fuction"
  ([name chop]
   (println (str "I " chop " chop " name "!")))
  ([name] (x-chop name "karate")))

(defn x-attack
  "Test variable arity function"
  [name & attacks]
  (println
   (str "I "
        (clojure.string/join
         ", "
         (drop-last attacks))
        " and " (last attacks)
        " " name)))

(defn chooser
  "Basic destructuring"
  [[first-choice second-choice & others]]
  (println (str "First choice: " first-choice))
  (println (str "Second choice: " second-choice))
  (println (str "Other choices: " (clojure.string/join ", " others))))

(defn announce-treasure-location
  "Map destructuring"
  [{lat :lat lng :lng}]
  (println (str "Treasure lat: " lat))
  (println (str "Treasure lng: " lng)))

(defn announce-gun-ratio
  "Map destructuring"
  [{:keys [guns butter]}]
  (println (str "Guns: " guns))
  (println (str "Butter: " butter))
  (println (str "Ratio guns/butter: " (/ guns butter))))

(defn special-sum
  "Example anonymous function with rest"
  [& args]
  (apply #(apply + %&) args))

(defn print-special-sum
  []
  (let [ls [1 2 3 4 5]]
    (println
     (str
      "Sum of "
      (clojure.string/join ", " ls)
      ": "
      (apply special-sum ls)))))

(defn inc-maker
  "Returning function"
  [n]
  #(+ % n))

(defn print-list
  "destructuring with let"
  [ls]
  (if (nil? ls) nil
      (let [[e & others] ls]
        (println e)
        (print-list others))))
