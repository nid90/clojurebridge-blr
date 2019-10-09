(ns land-of-wizards.core
  (:require [clojure.string :as str]
            [land-of-wizards.data :refer :all]))

(comment "The first command to have is a command that tells about the location the wizard is standing in. So what would a function need to describe a location in a world? Well, it would need to know the location to describe and would need to be able to look at a map and find that location on the map. Here's a function, that does exactly that")

(defn describe-location [location nodes]
  (get nodes location))

#_ (describe-location :living-room nodes)

(comment "The `describe-location` seems pretty awkward in several different ways. First of all, why are the variables for location and map being passed as parameters, instead of just reading the global variables directly? The reason is that Lispers often like to write code in the Functional Programming Style (To be clear, this is completely unrelated in any way to the concept called \"procedural programming\" or \"structural programming\"). In this style, the goal is to write functions that are referentially transaparent. An expression is called referentially transparent if it can be replaced with its corresponding value without changing the program's behavior, and to achive that:

1.  You only read variables that are passed into the function or are created by the function (So you don't read any global variables)
2.  You never change the value of a variable that has already been set (So no incrementing variables or other such foolishness)
3.  You never interact with the outside world, besides returning a result value. (So no writing to files, no writing messages for the user)")

(comment "Here's a function to describe the path from the wizard's current location to any given location")

(defn describe-path [edges from to]
  (let [[path obstacle] (to (from edges))]
    (str "There is a " (name obstacle) " going "
         (name path) " from here.")))

#_ (describe-path edges :living-room :attic)

(comment "Now there's a function to describe all paths in and out of wizard's current location. This function uses another common functional programming technique: The use of Higher Order Functions - This means that the map function is taking other functions as parameters so that they can call them themselves - map simply applies another function to every object in the collection (in this case a list) returned by `(keys (location edges))`, basically causing all paths to be changed into pretty descriptions procided by the describe-path function.")

(defn describe-all-paths [location edges]
  (str/join " " (map #(describe-path edges location %) (keys (location edges)))))

#_ (describe-all-paths edges :living-room)

(comment "A function to get all the objects at a given location. This is a function that does just that.")

(defn objects-at [location location->objects]
  (get location->objects location))

#_ (objects-at :living-room location->objects)

(comment "To describe all the objects at a given location, the `objects-at-location` function isfirst called to get a collection (in this case a vector) of all the objects at the location, and then invoke the function stored as `describe-object-whereabouts` on all elements of the collection by using `map`. `map` returns a lazy sequence of elements (which in this case are strings). The strings in this sequence are then `join`ed to form a single string.")

(defn describe-objects-at [location location->objects]
  (let [object-whereabouts-description #(str "You see a " (name %) " on the floor.")
        objects-at-location (objects-at location location->objects)]
    (str/join " " (map object-whereabouts-description objects-at-location))))

#_ (describe-objects-at :living-room location->objects)

(comment "`look` ties all these descriptor functions into a single, easy command called `look`` that uses the global variables (therefore this function is not in the Functional Style) to feed all the descriptor functions and describes everything")

(defn look [location]
  (str (describe-location location nodes) " "
       (describe-all-paths location edges) " "
       (describe-objects-at location location->objects)))

#_ (look :living-room)
