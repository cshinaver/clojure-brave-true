(ns clojure-noob.ch4.fwpd)

(def filename "resources/suspects.csv")

(def vamp-keys [:name :glitter-index])

(defn str->int
  [s]
  (Integer. s))

(def conversions {:name identity
                  :glitter-index str->int})

(defn convert
  [vamp-key value]
  ((get conversions vamp-key) value))

(defn parse
  "Convert a CSV into rows of columns"
  [string]
  (map #(clojure.string/split % #",")
       (clojure.string/split string #"\n")))

(defn mapify
  [rows]
  "Returns a seq of maps like {:name \"Edward Cullen\" :glitter-index 10}"
  (map 
   (fn [unmapped-row]
     (reduce
      (fn [row-map [vamp-key value]]
        (assoc row-map vamp-key (convert vamp-key value)))
      {}
      (map vector vamp-keys unmapped-row))
     )
   rows))

(defn glitter-filter
  [minimum-glitter records]
  (filter #(>= (:glitter-index %) minimum-glitter) records))

