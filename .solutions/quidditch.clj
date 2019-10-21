(defn start-game [team-1 team-2]
  {team-1   {:points 0, :opponent team-2},
   team-2   {:points 0, :opponent team-2},
   :started true
   :teams   [team-1 team-2]})

(defn score [game team]
  (update-in game [team :points] + 10))

(defn beat [game team]
  (update-in game [(get-in game [team :opponent]) :points] - 10))

(defn catch-snitch [game team]
  (-> game
      (assoc :started false)
      (update-in [team :points] + 150)))

(defn result [game]
  (let [team1 (first (:teams game))
        team2 (second (:teams game))

        team-1-points (get-in game [team1 :points])
        team-2-points (get-in game [team2 :points])]

    (if (> team-1-points team-2-points)

      (prn (str team1 " has won by " team-1-points " points!"))
      (prn (str team2 " has won by " team-2-points " points!")))))

