(ns clojure-noob.core
  (:use [clojure-noob misc hobbit])
  (:gen-class))

(defn -main
 "I don't do a whole lot ... yet."
 [& args]
 (x-chop "kanye" "ninja")
 (x-chop "jim")
 (x-attack "jim" "hit" "kick" "slap" "poke")
 (chooser ["herp" "derp" "merp" "slerp"])
 (announce-treasure-location {:lat 9.9 :lng 91.231})
 (announce-gun-ratio {:butter 50 :guns 2})
 (print-special-sum))
