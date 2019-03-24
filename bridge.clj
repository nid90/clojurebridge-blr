;; writing all expressions here to evalute in REPL

nil
1
2
true
false
(prn "Hello World")
(+ 3 4)
inc
'true
'"foo"
"foo"
(class 3/4)
(class nil)
(class \c)
(conj [1 2 3] 4)
(conj [1 2 3] 4 5)
(conj [1 2 3] "foo")
(+ 1 2)
(1 2 3 4)
((fn same [x] x) 42)
(let [x 1
      y 1
      z (+ x y)
      z (* 2 z)]
  (println z)
  x)
