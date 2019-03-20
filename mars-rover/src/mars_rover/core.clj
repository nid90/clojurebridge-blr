(ns mars-rover.core
  (:require [quil.core :as q :include-macros true]
            [quil.middleware :as m]))

(def mars [500 500])
(def mars-center (map #(/ % 2) mars))
(def rover-move-by 5)
(def directions [:N :E :W :S])
(def shifts {:N [0 (- rover-move-by)]
             :E [(- rover-move-by) 0]
             :W [0 (- rover-move-by)]
             :S [(- rover-move-by) 0]})
(def rover-width  25)
(def rover-height 50)
(def rover-in-the-middle-of-mars {:x (nth mars-center 0)
                                  :y (nth mars-center 1)
                                  :direction (nth directions 0)})

(defn setup []
  (q/frame-rate 30)
  {:rover rover-in-the-middle-of-mars})

(defn deploy-rover [{{:keys [x y] :as rover} :rover}]
  (q/rect x y rover-width rover-height))

(defn move-rover [{{:keys [x y direction] :as rover} :rover}]
  (assoc 
    rover 
    :x (+ x (first  (get shifts direction))) 
    :y (+ y (second (get shifts direction)))
    :direction direction))

(defn out-of-mars? [{{:keys [x y] :as rover} :rover}]
  (or (= x 0)
      (= y 0)))

(defn update-state [state]
  (if (out-of-mars? state)
    {:rover rover-in-the-middle-of-mars}
    state))

(defn draw-state [state]
  (q/background 89 0 0)
  (deploy-rover state))

(defn key-press [state e]
  (condp = (:key e)
    :up    (assoc state :rover (move-rover state))
    state))

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
