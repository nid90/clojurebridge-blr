(ns mars-rover.core
  (:require [quil.core :as q :include-macros true]
            [quil.middleware :as m]))

(def mars [500 500])
(def mars-center (map #(/ % 2) mars))
(def mars-center-x (nth mars-center 0))
(def mars-center-y (nth mars-center 1))
(def directions [:N :E :W :S])
(def rover-size 20)
(def rover-in-the-middle-of-mars {:x1 mars-center-x
                                  :y1 (- mars-center-y rover-size)
                                  :x2 (- mars-center-x rover-size)
                                  :y2 (+ mars-center-y rover-size)
                                  :x3 (+ mars-center-x rover-size)
                                  :y3 (+ mars-center-y rover-size)})

(defn setup []
  (q/frame-rate 30)
  {:rover rover-in-the-middle-of-mars})

(defn draw-rover [{:keys [rover]}]
  (q/triangle (:x1 rover) (:y1 rover)
              (:x2 rover) (:y2 rover)
              (:x3 rover) (:y3 rover)))

(def rover-move-by 10)

(defn move-rover [{:keys [rover]}]
  {:x1 (:x1 rover)
   :y1 (- (:y1 rover) rover-move-by)
   :x2 (:x2 rover)
   :y2 (- (:y2 rover) rover-move-by)
   :x3 (:x3 rover)
   :y3 (- (:y3 rover) rover-move-by)})

(defn key-press [state e]
  (condp = (:key e)
    :up    (assoc state :rover (move-rover state))
    :down  (assoc state :rover (move-rover state))
    :left  (assoc state :rover (move-rover state))
    :right (assoc state :rover (move-rover state))
    state))

(defn rotate-x [x y]
  (- (* x (Math/cos 90))
     (* y (Math/sin 90))))

(defn rotate-y [x y]
  (+ (* x (Math/sin 90))
     (* y (Math/cos 90))))

(defn turn-rover [{{:keys [x1 y1 x2 y2 x3 y3]} :rover}]
  {:x1 (rotate-x x1 y1)
   :y1 (rotate-y x1 y1)
   :x2 (rotate-x x2 y2)
   :y2 (rotate-y x2 y2)
   :x3 (rotate-x x3 y3)
   :y3 (rotate-y x3 y3)})

(defn out-of-mars? [{:keys [rover]}]
  (or (= (:x1 rover) 0)
      (= (:y1 rover) 0)))

(defn update-state [state]
  (if (out-of-mars? state)
    {:rover rover-in-the-middle-of-mars}
    state))

(defn draw-state [state]
  (q/background 89 0 0)
  (draw-rover state))

(q/defsketch hello-quils
  :title "Squad of robotic rovers"
  :size mars

  ;; setup function called only once, during sketch initialization.
  :setup setup

  ;; update-state is called on each iteration before draw-state.
  :update update-state
  :draw draw-state
  :features [:keep-on-top]
  :key-pressed key-press
  

  ;; This sketch uses functional-mode middleware.
  ;; Check quil wiki for more info about middlewares and particularly
  ;; fun-mode.
  :middleware [m/fun-mode])
