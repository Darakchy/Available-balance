# Available balance — maib card balance widget

[English](#english) · [Română](#română) · [Русский](#русский)

```
┌─────────────────────┐
│     100.00 MDL      │
│ •1234 · 09.11 12:30 │
└─────────────────────┘
```

---

## English

An Android app that shows the available balance of your **maib** card on the home screen.

When the bank sends a notification like

> Successful payment of 25.50 MDL at SHOP with card \*\*\*1234. Available balance: 100.00 MDL.

the app reads the balance from it and updates the widget. Payments, top-ups and P2P transfers are recognized — with the maibank app in English, Romanian or Russian.

### Privacy

- The app **does not use the internet** and sends nothing anywhere.
- It stores only the last 4 digits of the card, the last balance and its time — inside the app, on the phone.
- Only notifications from the maibank app are read; all other apps are ignored, and so are maib notifications for other cards.

### Installation

The app is in English, Romanian or Russian — it follows the phone language (English for any other language).

1. Download `app-debug.apk` from [Releases](../../releases) (or build it yourself, see below).
2. Open the file on the phone and allow installing from this source. If Google Play Protect warns about an unknown app, tap "Install anyway".
3. Open the **Balance** app:
   - enter the last 4 digits of your card and tap **Save**;
   - tap **Grant notification access** and turn on the switch for «Balance».
4. Add the widget: long-press an empty spot on the home screen → **Widgets** → **Balance**.

The balance appears after the next card transaction.

**If the widget stops updating** (happens on Samsung): Settings → Apps → Balance → Battery → **Unrestricted**.

### Building

Requires the Android SDK (installed with Android Studio).

```bash
./gradlew assembleDebug
```

The APK is at `app/build/outputs/apk/debug/app-debug.apk`.

Notification parsing tests:

```bash
./gradlew testDebugUnitTest
```

If there is no Java on the system, use the one bundled with Android Studio:

```bash
JAVA_HOME="/Applications/Android Studio.app/Contents/jbr/Contents/Home" ./gradlew assembleDebug
```

### How it works

| File | What it does |
|---|---|
| `Parser.kt` | Finds the card number and balance in the notification text |
| `BalanceListener.kt` | Receives notifications and saves the balance for your card |
| `BalanceWidget.kt` | The home screen widget |
| `MainActivity.kt` | Settings screen: card number and notification access |

If the bank changes its notification text, update the pattern in `Parser.kt` and add an example to `ParserTest.kt`.

### License

[MIT](LICENSE)

---

## Română

O aplicație Android care afișează pe ecranul principal soldul disponibil al cardului tău **maib**.

Când banca trimite o notificare de tipul

> Successful payment of 25.50 MDL at SHOP with card \*\*\*1234. Available balance: 100.00 MDL.

aplicația extrage soldul din ea și actualizează widgetul. Sunt recunoscute plățile, alimentările și transferurile P2P — cu aplicația maibank în engleză, română sau rusă.

### Confidențialitate

- Aplicația **nu folosește internetul** și nu trimite nimic nicăieri.
- Se păstrează doar ultimele 4 cifre ale cardului, ultimul sold și ora acestuia — în interiorul aplicației, pe telefon.
- Sunt citite doar notificările aplicației maibank; celelalte aplicații sunt ignorate, la fel și notificările maib pentru alte carduri.

### Instalare

Aplicația este în română, engleză sau rusă — urmează limba telefonului (engleză pentru orice altă limbă).

1. Descarcă `app-debug.apk` din [Releases](../../releases) (sau construiește-l singur, vezi mai jos).
2. Deschide fișierul pe telefon și permite instalarea din această sursă. Dacă Google Play Protect avertizează despre o aplicație necunoscută, apasă „Instalează oricum”.
3. Deschide aplicația **Sold**:
   - introdu ultimele 4 cifre ale cardului și apasă **Salvează**;
   - apasă **Acordă acces la notificări** și activează comutatorul pentru «Sold».
4. Adaugă widgetul: apasă lung pe un loc liber de pe ecranul principal → **Widgeturi** → **Sold**.

Soldul apare după următoarea operațiune cu cardul.

**Dacă widgetul nu se mai actualizează** (se întâmplă pe Samsung): Setări → Aplicații → Sold → Baterie → **Nerestricționat**.

### Construire

Este necesar Android SDK (se instalează împreună cu Android Studio).

```bash
./gradlew assembleDebug
```

APK-ul se află în `app/build/outputs/apk/debug/app-debug.apk`.

Teste pentru interpretarea notificărilor:

```bash
./gradlew testDebugUnitTest
```

Dacă în sistem nu există Java, folosește-o pe cea inclusă în Android Studio:

```bash
JAVA_HOME="/Applications/Android Studio.app/Contents/jbr/Contents/Home" ./gradlew assembleDebug
```

### Cum funcționează

| Fișier | Ce face |
|---|---|
| `Parser.kt` | Găsește numărul cardului și soldul în textul notificării |
| `BalanceListener.kt` | Primește notificările și salvează soldul pentru cardul tău |
| `BalanceWidget.kt` | Widgetul de pe ecranul principal |
| `MainActivity.kt` | Ecranul de setări: numărul cardului și accesul la notificări |

Dacă banca schimbă textul notificărilor, actualizează șablonul în `Parser.kt` și adaugă un exemplu în `ParserTest.kt`.

### Licență

[MIT](LICENSE)

---

## Русский

Android-приложение, которое показывает на рабочем столе остаток по вашей карте **maib**.

Когда приходит пуш от банка вида

> Successful payment of 25.50 MDL at SHOP with card \*\*\*1234. Available balance: 100.00 MDL.

приложение берёт из него остаток и обновляет виджет. Распознаются пуши об оплате, пополнении и переводах — при любом языке приложения maibank: английском, румынском или русском.

### Конфиденциальность

- Приложение **не использует интернет** и ничего никуда не отправляет.
- Сохраняются только последние 4 цифры карты, последний остаток и время — внутри приложения на телефоне.
- Читаются только уведомления приложения maibank; остальные приложения игнорируются, как и пуши maib по другим картам.

### Установка

Приложение на русском, румынском или английском — по языку телефона (английский для любого другого языка).

1. Скачайте `app-debug.apk` из раздела [Releases](../../releases) (или соберите сами — см. ниже).
2. Откройте файл на телефоне и разрешите установку из этого источника. Если Google Play Protect предупредит о неизвестном приложении — нажмите «Всё равно установить».
3. Откройте приложение **Остаток**:
   - введите последние 4 цифры своей карты и нажмите **Сохранить**;
   - нажмите **Дать доступ к уведомлениям** и включите переключатель у «Остаток».
4. Добавьте виджет: долгое нажатие на пустом месте рабочего стола → **Виджеты** → **Остаток**.

Остаток появится после следующей операции по карте.

**Если виджет перестал обновляться** (бывает на Samsung): Настройки → Приложения → Остаток → Батарея → **Без ограничений**.

### Сборка

Нужен Android SDK (ставится вместе с Android Studio).

```bash
./gradlew assembleDebug
```

Готовый APK: `app/build/outputs/apk/debug/app-debug.apk`.

Тесты разбора текста уведомлений:

```bash
./gradlew testDebugUnitTest
```

Если в системе нет Java, используйте встроенную в Android Studio:

```bash
JAVA_HOME="/Applications/Android Studio.app/Contents/jbr/Contents/Home" ./gradlew assembleDebug
```

### Как устроено

| Файл | Что делает |
|---|---|
| `Parser.kt` | Находит в тексте пуша номер карты и остаток |
| `BalanceListener.kt` | Получает уведомления и сохраняет остаток для вашей карты |
| `BalanceWidget.kt` | Виджет на рабочем столе |
| `MainActivity.kt` | Экран настройки: номер карты и доступ к уведомлениям |

Если банк поменяет текст уведомлений — поправьте шаблон в `Parser.kt` и добавьте пример в `ParserTest.kt`.

### Лицензия

[MIT](LICENSE)
