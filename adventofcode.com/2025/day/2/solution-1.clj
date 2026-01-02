(require 
  '[clojure.pprint :refer [pprint]]
  '[clojure.string :as str])

(defn parse-range [s] (map bigint (take 2 (str/split s #"-"))))
(defn parse-line [s] (str/split s #","))
(defn parse-input [ss] (map parse-range (parse-line (first ss))))

(defn valid-id? [id]
  (let [ id (str id)
         mid (/ (count id) 2) ]
    (or
      (odd? (count id))
      (= 2 (count (set (partition mid id)))))))
    
(defn invalid-in-range [r]
  (filter (complement valid-id?) (range (first r) (second r) 1)))
  
(->> (line-seq (java.io.BufferedReader. *in*))
  (parse-input)
  (map invalid-in-range)
  (flatten)
  (reduce +)
  (pprint))
