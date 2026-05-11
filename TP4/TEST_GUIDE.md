# TP4 – Tests

## Exercice 1
`java Exercise1` → affiche IP locale, puis résolution de noms.

## Exercice 2a
- Lancer `TimeServer`
- `java ClientChecker localhost` → affiche l'heure
- `java ClientChecker 192.168.1.99` (inexistant) → "inactif"

## Exercice 2b
`java RangeChecker 192.168.1.1` → teste 25 IPs

## Exercice 3
- `java PeriodicServer`
- `java PeriodicClient` → reçoit des messages périodiques, taper `stop` pour arrêter.

## Exercice 4
- Lancer `ProxyServer` (port 1026)
- Lancer `TimeServer` sur une autre machine (port 1027)
- `java ProxyClient` → taper IP du serveur distant → obtient l'heure relayée.