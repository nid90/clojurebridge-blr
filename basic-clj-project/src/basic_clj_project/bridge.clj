(ns basic-clj-project.bridge)

;; writing all expressions here to evaluate in REPL

nil
1
2
(comment (print (eval (read))))

(quote (+ 1 (- 4 (* 2 5))))

'()

;; (loop (print (eval (read))))
true
;false
(prn "Hello World")
(+ 1 2)

(+ 1, 2)
(+ 1)
(+ 1,,,,, 2)
(+, 1, 2)


(+ 1 2 3 4 5 6 7)

'(+ 1 2)
(+ 3 4)
(+ 1 (- 4 (* 2 5)))
()
(class ())
'(1 2 3)
inc
'true
'"foo"
"foo"
(class 3/4)
(class nil)
(class \c)

(comment (+ "invalid number" 2))
:foo
inc

(conj [1 2 3] 4)
(conj [1 2 3] 4 5)
(conj [1 2 3] "foo")
(+ 1 2)
(comment (1 2 3 4))
((fn same [x] x) 42)
(let [x 1
      y 1
      z (+ x y)
      z (* 2 z)]
  (println z)
  x)
{:first       "Harry"
 :middle     "James"
 :last       "Potter"
 :occupation "Auror"}
(assoc {:first "Hermione"} :last "Granger")
(dissoc {:first "Hermione" :last "Granger"} :last)
(merge {:first "Hermione"} {:last "Granger"})
(get {:first "Hermione" :last "Granger"} :first)
(get {:first "Hermione"} :last)
(get {:first "Hermione"} :last "NA")
(keys {:first "Hermione" :last "Granger"})
(vals {:first "Hermione" :last "Granger"})
(def hello {:count 1 :words "hello"})
(assoc hello :words "bye")
(update hello :count inc)
(update hello :words str ", world")

(def wizards [{:name  "Harry Pooter"
               :house "Gryffindor"}
              {:name  "Draco Malfoy"
               :house "Slytherin"}])

(def houses {:gryffindor {:colors ["scarlet" "gold"]
                          :points 200}
             :slytherin  {:colors ["green" "silver"]
                          :points 150}})
