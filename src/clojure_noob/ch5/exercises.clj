(ns clojure-noob.ch5.exercises)

(def attr (comp :intelligence :attributes))

((comp dec inc) 2)

(defn mycomp
  [& fns]
  (if (= (count fns) 1)
    (fn [& args]
      (apply (first fns) args))
    (fn [& args]
      ((first fns) (apply (apply mycomp (rest fns)) args)))))

(defn assoc-in
  [m [k & ks] v]
  (if (nil? ks)
    (assoc m k v)
    (assoc m k (assoc-in (get m k) ks v))))

(defn my-update-in
  [m [k & ks] f & args]
  (if (nil? ks)
    (assoc m k (apply f (m k) args))
    (assoc m k (apply assoc-in (m k) ks f args))))
