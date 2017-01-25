(ns clojure-noob.core
  (:gen-class))

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
  [{lat :lat lng :lng}]
  (println (str "Treasure lat: " lat))
  (println (str "Treasure lng: " lng)))

(defn announce-gun-ratio
  [{:keys [guns butter]}]
  (println (str "Guns: " guns))
  (println (str "Butter: " butter))
  (println (str "Ratio guns/butter: " (/ guns butter))))


(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (x-chop "kanye" "ninja")
  (x-chop "jim")
  (x-attack "jim" "hit" "kick" "slap" "poke")
  (chooser ["herp" "derp" "merp" "slerp"])
  (announce-treasure-location {:lat 9.9 :lng 91.231})
  (announce-gun-ratio {:butter 50 :guns 2})
  )
