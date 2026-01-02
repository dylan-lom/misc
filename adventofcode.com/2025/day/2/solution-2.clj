(require 
  '[clojure.pprint :refer [pprint]]
  '[clojure.string :as str]
  '[clojure.math :as math])

(defn parse-range [s] (map bigint (take 2 (str/split s #"-"))))
(defn parse-line [s] (str/split s #","))
(defn parse-input [ss] (map parse-range (parse-line (first ss))))

(defn partition-id [id]
  (let [id (str id)
        len (count id)
        mid (int (/ len 2))]
    (->> (range mid 0 -1)
      (filter (fn [i] (= (mod len i) 0)))
      (map (fn [i] (partition i id))))))

(defn valid-id? [id]
  (->>
    (partition-id id)
    (map set)
    (every? (fn [ss] (not= 1 (count ss))))))
    
(defn invalid-in-range [r]
  (filter (complement valid-id?) (range (first r) (inc (second r)) 1)))
  
(->> (line-seq (java.io.BufferedReader. *in*))
  (parse-input)
  (map invalid-in-range)
  (flatten)
  (reduce +)
  (pprint))
