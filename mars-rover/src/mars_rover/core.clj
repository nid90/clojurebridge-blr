(ns mars-rover.core
  (:require [quil.core :as q :include-macros true]
            [quil.middleware :as m]))

(def mars [500 500])
(def mars-center (map #(/ % 2) mars))
(def rover-move-by 2)
(def directions [:N :E :W :S])
(def shifts {:N [0 (- rover-move-by)]
             :E [(+ rover-move-by) 0]
             :W [(- rover-move-by) 0]
             :S [0 (+ rover-move-by)]})
(def turns {:N {:right :E :left :W}
            :E {:right :S :left :N}
            :W {:right :N :left :S}
            :S {:right :W :left :E}})
(def rover-size 25)
(def rover-in-the-middle-of-mars {:x (nth mars-center 0)
                                  :y (nth mars-center 1)
                                  :direction (nth directions 0)})

(defn setup []
  (q/frame-rate 30)
  {:rover rover-in-the-middle-of-mars})

(defn deploy-rover [{{:keys [x y] :as rover} :rover}]
  (q/rect x y rover-size rover-size))

(defn move-rover [{{:keys [x y direction] :as rover} :rover}]
  (assoc 
    rover 
    :x (+ x (first  (get shifts direction))) 
    :y (+ y (second (get shifts direction)))
    :direction direction))

(defn turn-rover [{{:keys [direction] :as rover} :rover} turn]
  (assoc
    rover
    :direction (get-in turns [direction turn])))

(defn out-of-mars? [{{:keys [x y] :as rover} :rover}]
  (or (= x 0)
      (= y 0)
      (= x (nth mars 0))
      (= y (nth mars 1))))

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
    :left  (assoc state :rover (turn-rover state :left))
    :right (assoc state :rover (turn-rover state :right))
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
