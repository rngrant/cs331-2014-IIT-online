
(ns l_system_lab.student)

(defn remove-empties
  [v]
  (filter #(not= [] %) v))

;;
;; You will need to rewrite this.  This code is just for show.
(defn get-xy-scale
  "Get the scaling factor for the coordinates."
   ([v]  (loop [vv v
                minix 1000.0
                miniy 1000.0
                macx -1.0
                macy -1.0 ]
           (if (empty? vv)
                  (get-xy-scale v minix miniy macx macy)
                 (recur (rest vv)
                        (min minix (nth (first vv) 1) (nth (first vv) 3))
                        (min miniy (min (nth (first vv) 2) (nth (first vv) 4)))
                        (max macx (max (nth (first vv) 1) (nth (first vv) 3)))
                        (max macy (max (nth (first vv) 2) (nth (first vv) 4)))
                        ))))
  ([v min-x min-y max-x max-y]
     {:scale (/ 480.0 (max (- max-x min-x) (- max-y min-y)))
      :min-x min-x :min-y min-y}))

(get-xy-scale [[:line 1 2 3 4] [:line 9 8 7 6]])

(defn scale-aux-aux [v scale mini n]
  (+ 10 (* scale (- (nth v n) mini))))

(defn scale-aux [v scale min-x min-y]
  (conj [:line] (scale-aux-aux v scale min-x 1)
                     (scale-aux-aux v scale min-y 2)
                          (scale-aux-aux v scale min-x 3)
                                 (scale-aux-aux v scale min-y 4 )))


(apply conj [1 2] [3 4 6 9 0 43 1])

;; You will need to rewrite this.  This code is just for show.
(defn scale-turtle
  "Normalizes a list of [:line ... ] vectors."
  ([v] (scale-turtle v (get-xy-scale (remove-empties v))))
  ([v scaleVal]
     (loop [vv v
            scale (:scale scaleVal)
            min-x (:min-x scaleVal)
            min-y (:min-y scaleVal)
            out []]
       (if (empty? vv) out
         (recur (rest vv) scale min-x min-y (conj out (scale-aux (first vv) scale min-x min-y) ))))))


(scale-turtle [[:line 1 2 3 4] [:line 9 8 7 6][:line 9 8 7 6]])


(defn transform
  [init-pat rules]
  (loop [
         i 0
         j (count init-pat)
         out []]
    (cond (>= i (count init-pat)) out
     (contains? rules (init-pat i)) (recur (inc i) j (apply conj out ((init-pat i) rules)));;problems right here
          :else (recur (inc i) j (conj  out (init-pat i))))) ;; Make it do something here!
)


;;------------------------------------------------------------------
;; # Some fractals to start out with.  Add some of your own!
