(ns hello-quil.1_random_walk
  (:require [quil.core :as q]
            [quil.middleware :as m]))

(defn setup []
  (q/frame-rate 30)
  (q/color-mode :hsb)
  {:x (/ 500 2)
   :y (/ 500 2)})

(defn update-state [state]
  (def x (:x state))
  (def y (:y state))

  ;range starts from the 0th, duh
  (def tx (rand-nth (range -2 3)))
  (def ty (rand-nth (range -2 3)))

  (if (>= x 498) (def tx 0))
  (if (>= y 498) (def ty 0))

  {:x (+ tx x)
   :y (+ ty y)})

(defn draw-state [state]
  (q/stroke 0)
  (q/fill 175)
  (q/rect-mode :center )
  (q/rect (:x state) (:y state) 4 4))

(q/defsketch hello-quil
  :title "random walk"
  :size [500 500]
  :setup setup
  :update update-state
  :draw draw-state
  :middleware [m/fun-mode])
