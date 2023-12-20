## Python razvijalec - naloga

### Tehnologije:

- Python verrzija 3.9 ali več
- Selenium
- Chrome brskalnik
- Poljubna spletna denarnica (Phantom(Solana, EVM), Keplr(Tendermint chains), Leo(Aleo), Rabby(EVM)

### Cilj:
- S pomočjo jezika Python avtomatiziraj upravljanje s spletno denarnico 

### Opis naloge:

Napišite Python skripto, v kateri z uporabo knjižnice Selenium implementirate naslednje: 
- Odpri Chrome brskalnik.
- Uvozi ctx poljublne spletne denarnice (pomoč za pridovitev ctx: https://chromewebstore.google.com/detail/crx-extractordownloader/ajkhmmldknmfjnmeedkbkkojgobmljda)
- Odpri spletno denarnico na način "chrome-extension://<extension_id>/popup.html"
- Prijavi se v spletno denarnico (s private key ali phrase list) in iz nje preberi naslov (npr. 0x8cdE5C02A602A2B7bFBBEC0041f2a42a98f9e39e).
- Preko RPC klica na blockchain-u po vaši izbiri, združljivim z izbrano denarnico, pridobi stanje ("balance"). Omrežje lahko deluje v testnem ali glavnem (mainnet) načinu. Stanje denarnice lahko znaša 0.

#### Primer RPC klica za ETH:


```
data = {
    "jsonrpc": "2.0",
    "method": "eth_getBalance",
    "params": [address, "latest"],
    "id": 1,
    }
response = requests.post(rpc_url, json=data)
```

#### Hint
Izberi si spletno denarnico, ki ima javno dostopni api endpoint

#### Primeri spletnih denarnic:
*[Rabby](https://rabby.io/)*.

*[Phantom](https://phantom.app/)*.

*[Keplr](https://www.keplr.app/)*.

*[Leo](https://www.leo.app/)*.



### Tehnične zahteve:

- Pregledna koda
- Implementirajte ponovno uporabne komponente kjer se vam to zdi smiselno
- Obravnavajte stanja nalaganja in napak (loading and errors)

### Navodila za oddajo:

- Priložite navodila za zagon aplikacije in operacijski sistem, na katerem ste aplikacijo testirali,
- **Ne oddajajte .crx datotek. Jasno naj bo le, katero denarnico ste uporabili.**
- Na githubu ustvarite novo vejo - \<dev-ime\> in oddajte kodo brez odvečnih datotek (**pycache**, /venv ...)
- Za pomoč se obrnite na info@adepto.us
