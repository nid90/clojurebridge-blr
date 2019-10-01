(ns land-of-wizards.core
  (:require [land-of-wizards.data :refer [nodes edges objects object-locations]]
            [clojure.string :as str]))

(comment "The first command we'd want to have is a command that tells us about the location we're standing in. So what would a function need to describe a location in a world? Well, it would need to know the location we want to describe and would need to be able to look at a map and find that location on the map. Here's our function, and it does exactly that")

(defn describe-location [location nodes]
  (get nodes location))

(describe-location :living-room nodes)

(comment
 "You may have noticed that our describe-location function seems pretty awkward in several different ways. First of all, why are we passing in the variables for location and map as parameters, instead of just reading our global variables directly? The reason is that Lispers often like to write code in the Functional Programming Style (To be clear, this is completely unrelated in any way to the concept called \"procedural programming\" or \"structural programming\"). In this style, the goal is to write functions that are referentially transaparent. An expression is called referentially transparent if it can be replaced with its corresponding value without changing the program's behavior, and to achive that:

1.  You only read variables that are passed into the function or are created by the function (So you don't read any global variables)
2.  You never change the value of a variable that has already been set (So no incrementing variables or other such foolishness)
3.  You never interact with the outside world, besides returning a result value. (So no writing to files, no writing messages for the user)")

(comment "Let's write a function to describe the path from the wizard's current location to any given location")

(defn describe-path [edges from to]
  (let [[path obstacle] (to (from edges))]
    (str "There is a " (name obstacle) " going "
         (name path) " from here.")))

(describe-path edges :living-room :attic)

(comment "Now a function to describe all paths in and out of wizard's current location. This function uses another common functional programming technique: The use of Higher Order Functions - This means that the map function is taking other functions as parameters so that they can call them themselves - map simply applies another function to every object in the collection (in this case a list) returned by `(keys (location edges))`, basically causing all paths to be changed into pretty descriptions procided by the describe-path function.")

(defn describe-all-paths
  [edges location]
  (str/join " " (map #(describe-path edges location %) (keys (location edges)))))

(describe-all-paths edges :living-room)

(defn objects-at
  [location object-locations]
  (map first (get (group-by second object-locations) location)))

(objects-at :living-room object-locations)

(defn describe-objects [location object-locations]
  (let [object-whereabouts #(str "You see a " (name %) " on the floor.")]
    (str/join " " (map object-whereabouts (objects-at location object-locations)))))

(describe-objects :living-room object-locations)

(comment "Now we can tie all these descriptor functions into a single, easy command called look that uses the global variables (therefore this function is not in the Functional Style) to feed all the descriptor functions and describes everything")

(defn look [location]
  (str (describe-location location nodes) " "
       (describe-all-paths location edges) " "
       (describe-objects location object-locations)))

(look :living-room)
