(ns clojure-noob.ch4.exercises
  (:use clojure-noob.ch4.fwpd))

;1
(map :name (glitter-filter 3 (mapify (parse (slurp filename)))))

;2
(def my-append
  (partial conj ["Edward Cullen" "Jacob Black" "Carlisle Cullen"]))

;3
(defn validate
  [validations record]
  (if (and
       ((validations :name) (:name record))
       ((validations :glitter-index) (:glitter-index record)))
    (my-append record)))

;4
(def maps (mapify (parse (slurp filename))))

(defn csvify
  [maps]
  (clojure.string/join
   "\n"
   (map
    #(clojure.string/join "," [(:name %) (:glitter-index %)])
        maps)))



          
