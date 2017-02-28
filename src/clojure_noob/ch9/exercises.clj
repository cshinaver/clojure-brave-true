(ns clojure-noob.ch9.exercises
  (:require [net.cgrand.enlive-html :as html]
            [clj-http.client :as client]))

(defn get-links-from-bing-soup
  [soup]
  (map (comp :href :attrs) (html/select soup [:li.b_algo :h2 :a])))

(defn search-bing
  [query]
  (let [formatted-query (clojure.string/replace query #" " "+")
        url (str "https://www.bing.com/search?q=" formatted-query)
        page (slurp url)
        soup (html/html-snippet page)]
    (slurp (first (get-links-from-bing-soup soup)))))
