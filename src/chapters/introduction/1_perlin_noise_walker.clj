(ns hello-quil.1_random_walk
  (:require [quil.core :as q]
            [quil.middleware :as m]))

(defn setup []
  (q/frame-rate 30)
  (q/color-mode :hsb)
  {:x (/ (q/width) 2)
   :y (/ (q/height) 2)
   :timex 0
   :timey 10000})

(defn update-state [state]
  (def x (:x state))
  (def y (:y state))

  (def timex (:timex state))
  (def timey (:timey state))

  (def noise_x (q/noise timex))
  (def noise_y (q/noise timey))

  (def tx (q/map-range noise_x, 0, 1, 0, (q/width)))
  (def ty (q/map-range noise_y, 0, 1, 0, (q/height)))
  (if (>= x (q/width)) (def tx 0))
  (if (>= y (q/height)) (def ty 0))

  {:x tx
   :y ty
   :timex (+ timex 0.008)
   :timey (+ timey 0.008)})

(defn draw-state [state]
  (q/background 255)
  (q/stroke 0)
  (q/fill 175)
  (q/rect-mode :center )
  (q/ellipse (:x state) (:y state) 40 40))

(q/defsketch hello-quil
  :title "random walk"
  :size [500 500]
  :setup setup
  :update update-state
  :draw draw-state
  :middleware [m/fun-mode])
