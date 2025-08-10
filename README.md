#### 🩺 Hasta Kayıt Sistemi — Java & OOP & SOLID Projesi

Bu proje, Java programlama dili ve Nesne Yönelimli Programlama (OOP) prensipleri kullanılarak geliştirilmiş modern, kullanımı kolay bir Hasta Kayıt Sistemidir. Sağlık sektöründe hasta verilerinin doğru ve güvenli yönetimi, hasta bakım kalitesini doğrudan etkiler. Bu nedenle, bu sistem hastaların temel bilgilerini benzersiz ID’ler ile kaydederek, güncelleyerek, listeleyerek ve silebilmeyi sağlar.

Projede, hasta verileri hafızada hızlı erişim sağlayan HashMap veri yapısında tutulurken, kalıcı kayıt için sade ve taşınabilir text dosyası kullanılmıştır. Böylece uygulama, kapandıktan sonra bile hasta bilgilerini kaybedecek endişeyi ortadan kaldırır.

Bu yaklaşım, kullanıcıların veri bütünlüğü ve erişilebilirlik ihtiyaçlarını karşılayarak, veritabanı olmadan dosyayı sanki veritabanıymış gibi kullanarak sağlık alanında verimli hasta yönetimine katkı sağlar.

| Tanıtım Resmi 1 |
|---------|
| <img width="1919" height="1079" alt="001_screenshot" src="https://github.com/user-attachments/assets/93c4de0a-c6d7-4703-95f9-092af15520d3" /> |

#### ⚙️ Proje Özellikleri
##### 1️⃣ Hasta Kaydetme
Kullanıcı, hastanın adını, soyadını, yaşını, telefon ve e-posta bilgilerini girerek yeni hasta oluşturur.

Her hasta için sistem tarafından benzersiz ve eşsiz bir ID otomatik üretilir.

Bu ID sayesinde hastalar karışıklığa mahal vermeden net şekilde tanımlanır.

##### 2️⃣ Hasta Listeleme
Kayıtlı tüm hastaların bilgileri, kullanıcıya tablo halinde sunulur.

ID, ad, soyad, yaş, telefon ve e-posta gibi kritik bilgiler kolayca erişilebilir.

Bu özellik sayesinde sağlık personeli hastaları hızlıca görebilir ve inceleyebilir.

3️⃣ Hasta Güncelleme
Kullanıcı mevcut hastanın bilgilerini ID üzerinden seçip, ad, soyad, yaş, telefon, e-posta gibi detayları güncelleyebilir.

Hasta verilerinin güncel ve doğru tutulması, tedavi ve iletişim açısından kritik önem taşır.

##### 4️⃣ Hasta Silme
Kullanıcı, istenmeyen veya eski hastaları sistemden ID ile güvenle silebilir.

Silme işlemi hem HashMap’ten hem de kalıcı kayıt olan text dosyasından yapılır, böylece veri tutarlılığı sağlanır.

##### 5️⃣ Veri Kaydetme ve Yükleme
Tüm hasta verileri, uygulama kapanmadan önce text dosyasına kaydedilir, böylece kalıcılık sağlanır.

Program açıldığında, önceden kaydedilmiş hasta verileri dosyadan otomatik yüklenerek kullanıcıya sunulur.

Bu, veri kaybını engeller ve kullanım kolaylığı sağlar.

##### 🚀 Teknolojiler ve Yaklaşımlar
###### 💡Java SE: 
Java, nesne yönelimli, platform bağımsız, yüksek performanslı ve hem derlenen hem yorumlanan programlama dili, teknoloji imparatorluğudur; web uygulamaları, mobil uygulamalar (Android), büyük veri sistemleri ve gömülü sistemler gibi geniş bir kullanım alanına sahiptir.

###### 💡Nesne Yönelimli Programlama (OOP): 
OOP (Nesne Yönelimli Programlama), yazılımı gerçek dünya nesneleriyle modelleyerek daha organize, esnek ve bakımı kolay kod yazmayı sağlayan bir programlama yaklaşımıdır.

###### 💡HashMap: 
HashMap, Java'da anahtar-değer (key-value) çiftlerini depolayan, hızlı erişim sağlayan ve düzensiz sıralamaya sahip bir hash tabanlı koleksiyon sınıfıdır. Hızlı ve etkin veri erişimi için kullanıldı.

###### 💡Text Dosyası: 
Basit, taşınabilir ve kullanıcı dostu veri depolama, text dosyası formatında ama ".data" uzantılı adeta database gibi.

###### 💡Swing GUI: 
Kullanıcı dostu, Java'ya ait ve estetik grafik arayüz.

###### 💡SOLID Prensipler: 
Yazılım tasarımında sürdürülebilir, esnek ve anlaşılır kod yazmak için kullanılan beş temel ilkedir. Bakımı kolay, genişletilebilir ve tekrar kullanılabilir yazılım geliştirmek amacıyla uygulanır. 

🎯 Sonuç
Bu proje, hasta verilerinin güvenilir, kolay erişilebilir ve güncellenebilir bir biçimde yönetilmesini sağlayarak sağlık personelinin işini kolaylaştırır. Aynı zamanda, programlama öğrenenler için gerçek dünya uygulaması olarak pratik ve kapsamlı bir deneyim sunar. Kaliteli hasta takibi ve veri yönetimi için sağlam bir temel oluşturur.
