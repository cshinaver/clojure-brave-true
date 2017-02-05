(ns clojure-noob.ch3.exercises)

; 1
(str "hey" "my" "name" "is" "bob")
(vector 1 2 [23 2] '(12) "hi")
(list 1 2 4 4 1 23 3)
(hash-map :a 1 :b 2 :c 3)
(hash-set 1 1 1 2 3 4 1 2 4 )

; 2
(defn add-100
  [x]
  (+ x 100))

; 3
(defn dec-maker
  [n]
  #(- % 9))

; 4
(defn mapset
  [f coll]
  (set (map f coll)))


; 5
(def alien-parts
  [{:name "head" :size 3}
   {:name "left-eye" :size 2}
   {:name "left-leg" :size 1}
   {:name "left-hand" :size 4}])

(defn matching-alien-parts
  [part]
  (let [part-list ["back-" "upper-left-" "upper-right-" "right-"]]
    (map #(hash-map
           :name (clojure.string/replace (:name part) #"^left-" %)
           :size (:size part))
         part-list)))

(defn alien-symmetrize-body-parts
  [asym-parts]
  (reduce
   (fn [a part]
     (into a (set (apply vector part (matching-alien-parts part)))))
   []
   asym-parts))


(defn general-matching-alien-parts
  [part desired-parts]
  (map #(hash-map
         :name (clojure.string/replace (:name part) #"^left-" %)
         :size (:size part))
       desired-parts))

(defn general-symmetrize-body-parts
  [asym-parts desired-parts]
  (reduce
   (fn [a part]
     (into a (set (apply vector part (general-matching-alien-parts part desired-parts)))))
   []
   asym-parts))
