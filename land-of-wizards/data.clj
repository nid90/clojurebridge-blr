(ns land-of-wizards.data)

(comment "Clojure provides a data structure called a Map. A Map is created as mappings of values to their respective keys like so:  order key1 value1 key2 value2... The nodes variable is also a Map - the three keys here living-room, garden, and attic. The following maps contain everything important to know about the three locations:")

(comment "The node map contains a unique name for each location as a key (i.e. house, garden, and attic) and a short description of what can be seen from each of these locations, which is mapped to their respective keys as their values.")

(def nodes
  {:living-room "You are in the living-room. A wizard is snoring loudly on the couch."
   :garden "You are in a beautiful garden. There is a well in front of you."
   :attic "You are in the attic. There is a giant welding torch in the corner."})

(comment "Let's keep track of the wizard's movements.")

(def wizard-location :living-room)

(comment "The edges map contains information about the where and how of each path into/out of that place. Notice how information-rich this one variable is and how it describes all that's  needed to know but not a thing more - Lispers love to create small, concise pieces of code that leave out any fat and are easy to understand just by looking at them.")

(def edges
  {:living-room {:garden [:west :door]
                 :attic  [:upstairs :ladder]}
   :garden      {:living-room [:east :door]}
   :attic       {:living-room [:downstairs :ladder]}})

(comment "The game is going to have some objects in it that the player can pick up and use")

(def objects [:whiskey :bucket :frog :chain])

(comment "Now since there's a map and a bunch of objects, it makes sense to create another variable that says where each of these object is on the map:")

(def location->objects {:living-room [:whiskey :bucket]
                        :garden      [:chain :frog]})

(comment "Given the information above, we intend to:
1. Describe the paths from wizard's current location to another given location.
2. Describe all the paths in and out of wizard's current location
3. Describe all the objects at wizard's current location
; TODO: elaborate this. how can you do all this in 1 fn:
4. Write a function that ties up all the above functionality")
