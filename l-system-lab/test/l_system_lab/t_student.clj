(ns l_system_lab.t-student
  (:use midje.sweet)
  (:use [l_system_lab.student]))

(facts "about numbers"
       (fact "Floating point is close enough sometimes."
             10.0 => (roughly 10.001)
             9.0 => (roughly 9.0)))

(facts "About get-xy-scale"
       (fact "Gives correct response for example problems."
             (get-xy-scale [[:line 1 2 3 4] [:line 9 8 7 6]]) => {:scale 60 , :min-x 1, :min-y 2})
       (fact "works for more than 2 :line vectors"
             (get-xy-scale [[:line 1 2 3 4] [:line 9 8 7 6][:line 9 8 7 6]]) => {:scale 60 , :min-x 1, :min-y 2}))

(facts "About scale-turtle."
       (fact "Scales correctly with example problems."
             (scale-turtle [[:line 1 2 3 4] [:line 9 8 7 6]])=> [[:line 10 10 130 130][:line 490 370 370 250]])
       (fact "Scales correctly with more than 2 :line vectors"
             (scale-turtle [[:line 1 2 3 4] [:line 9 8 7 6][:line 9 8 7 6]])=> [[:line 10 10 130 130]
                                                                                 [:line 490 370 370 250]
                                                                                 [:line 490 370 370 250]]))
(facts "About transform."
       (fact "works with example problems."
             (transform (:init koch-curve) (:rules koch-curve))=>
             [:f :+ :f :- :- :f :+ :f :- :- :f :+ :f :- :- :f :+ :f :- :- :f :+ :f :- :- :f :+ :f])
       (fact "works multiple times"
             (transform (transform (:init koch-curve) (:rules koch-curve)) (:rules koch-curve))=>
             [:f :+ :f :- :- :f :+ :f :+ :f :+ :f :- :- :f :+ :f :- :- :f :+ :f :- :- :f :+ :f :+
              :f :+ :f :- :- :f :+ :f :- :- :f :+ :f :- :- :f :+ :f :+ :f :+ :f :- :- :f :+ :f :-
              :- :f :+ :f :- :- :f :+ :f :+ :f :+ :f :- :- :f :+ :f :- :- :f :+ :f :- :- :f :+ :f
              :+ :f :+ :f :- :- :f :+ :f :- :- :f :+ :f :- :- :f :+ :f :+ :f :+ :f :- :- :f :+ :f])
       )

