(ns clojure-noob.ch4.reading)

(defn titleize
  [topic]
  (str topic " for the Brave and True"))

(map titleize ["Hampsters" "Ragnarok"])

(map titleize '("Hampsters" "Ragnarok"))
