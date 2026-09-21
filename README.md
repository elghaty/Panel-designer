# مصمم الشبكات الكهربائية — مشروع أندرويد

تطبيق أندرويد حقيقي (WebView) بيحمّل نفس أداة تصميم اللوحات الكهربائية اللي
اشتغلنا عليها (حسابات الأحمال، الكابلات، القواطع، ومخطط اللوحة SLD) — لكن
شغّالة بالكامل على الجهاز نفسه (offline)، بدون Tailwind CDN ولا خطوط جوجل،
والبيانات بتتخزن محلياً على الموبايل (localStorage) بدون إنترنت.

## هيكل المشروع

```
ElectricalPanelDesigner/
├── app/
│   ├── build.gradle
│   └── src/main/
│       ├── AndroidManifest.xml
│       ├── java/com/basha/paneldesigner/MainActivity.kt   ← يفتح WebView ويحمّل assets/index.html
│       ├── assets/
│       │   ├── index.html      ← التطبيق كامل (HTML/CSS/JS)
│       │   └── mini-utils.css  ← بديل خفيف لـ Tailwind يشتغل بدون إنترنت
│       └── res/                ← أيقونة التطبيق والثيم
├── build.gradle
├── settings.gradle
└── gradle.properties
```

## إزاي تطلّع منه APK

1. نزّل **Android Studio** (أحدث إصدار) من الموقع الرسمي.
2. `File → Open` واختار مجلد `ElectricalPanelDesigner`.
3. سيب Android Studio يعمل **Gradle Sync** (محتاج إنترنت في الخطوة دي بس، عشان
   ينزّل Gradle wrapper وأدوات البناء لأول مرة).
4. لتجربته على موبايلك مباشرة: وصّل الموبايل بالكابل وفعّل **USB debugging**،
   وبعدين دوس **Run ▶**.
5. لطلوع ملف APK تنزله وتنصبه يدوياً على أي جهاز:
   `Build → Build Bundle(s) / APK(s) → Build APK(s)`
   وهيطلعلك الملف في:
   `app/build/outputs/apk/debug/app-debug.apk`
6. لو عايزه APK نهائي موقّع (Release) تقدر تنزله فعلياً على جوجل بلاي:
   `Build → Generate Signed Bundle / APK` واعمل مفتاح توقيع (Keystore) جديد
   واتبع الخطوات.

## ملاحظات مهمة

- **البيانات محلية بس**: كل مشروع/لوحة بتحفظها بتتخزن على الموبايل نفسه فقط
  (localStorage جوه WebView) — مفيش سيرفر ومفيش مزامنة بين الأجهزة. لو غيّرت
  الموبايل، هتحتاج تصدّر بياناتك يدوياً (ممكن نضيف زرار تصدير/استيراد JSON
  لاحقاً لو حابب).
- **التطبيق شغّال بدون إنترنت بالكامل** بعد ما يتثبت، لأنه بيحمّل كل حاجة من
  ملفات الـ assets المحلية.
- لو عايز تغيّر اسم الحزمة (applicationId) أو اسم التطبيق، عدّل في
  `app/build.gradle` (`applicationId`) و `res/values/strings.xml` (`app_name`).
- الحسابات نفسها (جداول الكابلات، القواطع، هبوط الجهد) لسه تقريبية للتصميم
  الأولي فقط — لازم اعتمادها من مهندس كهرباء مرخّص قبل التنفيذ الفعلي.
