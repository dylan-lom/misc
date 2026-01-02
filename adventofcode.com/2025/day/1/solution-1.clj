(require '[clojure.pprint :refer [pprint]])

(defn parse-line [s]
  (let [chars (seq s)
        dir (if (= \L (first chars)) -1 1)
        deg (Integer/parseInt (apply str (rest chars)))
        rot (* deg dir)]
    rot))

(->> (line-seq (java.io.BufferedReader. *in*))
  (map parse-line)
  (reductions + 50)
  (filter (fn [deg] (= 0 (mod deg 100))))
  (count)
  (println))

;; (pprint (count
;;   (filter (fn [deg] (= 0 (mod deg 100)))
;;     (reductions + 50
;;       (map parse-line (line-seq (java.io.BufferedReader. *in*)))))))
