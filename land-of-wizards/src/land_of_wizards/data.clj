(ns land-of-wizards.data)

(comment "Clojure provides a data structure called a Map. A Map is created by as mappings of values to their respective keys like so:  order key1 value1 key2 value2... Our game-map variable is also a Map - the three keys here living-room, garden, and attic. The following maps contain everything important that we'd like to know about our three locations:")

(comment  "The node map contains a unique name for each location as a key (i.e. house, garden, and attic) and a short description of what we can see from each of these locations, which is mapped to their respective keys as their values.")

(def nodes
  {:living-room "You are in the living-room. A wizard is snoring loudly on the couch."
   :garden "You are in a beautiful garden. There is a well in front of you."
   :attic "You are in the attic. There is a giant welding torch in the corner."})

(comment  "The edges map contains information about the where and how of each path into/out of that place. Notice how information-rich this one variable is and how it describes all we need to know but not a thing more - Lispers love to create small, concise pieces of code that leave out any fat and are easy to understand just by looking at them.")

(def edges
  {:living-room {:garden [:west :door],
                 :attic  [:upstairs :ladder]}
   :garden      {:living-room [:east :door]}
   :attic       {:living-room [:downstairs :ladder]}})

(comment "The game is going to have some objects in it that the player can pick up and use")

(def objects [:whiskey :bucket :frog :chain])

(comment "Now that we have a map and a bunch of objects, it makes sense to create another variable that says where each of these object is on the map:")

(def object-locations {:whiskey :living-room
                       :bucket  :living-room
                       :chain   :garden
                       :frog    :garden})

(comment  "Given the information above, we intend to:
1. Describe the location the wizard is at.
2. Describe the paths from wizard's current location to another given location.
3. Describe all the paths in and out of wizard's current location
4. Describe all the objects at wizard's current location
5. Write a function that ties up all the above functionality to")
