# 📸 P2PHOTO – Sistema de Partilha de Fotos

![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Android Studio](https://img.shields.io/badge/Android_Studio-3DDC84?style=for-the-badge&logo=android-studio&logoColor=white)
![API](https://img.shields.io/badge/API-REST-blue?style=for-the-badge)

> **Descrição:** Aplicação móvel nativa para partilha de fotos e gestão de álbuns. Conectada à API **P2PHOTO_API** para autenticação e sincronização de dados em tempo real.

![Demo da Aplicação](./app/src/main/res/demo-app.png)
*(Dica: Adiciona screenshots reais da aplicação – Login, Galeria, Álbum)*

---

## 📌 Sobre o Projecto

O **P2PHOTO** é uma aplicação Android que permite aos utilizadores criar e partilhar álbuns de fotos com outros utilizadores da plataforma. O sistema foca-se na experiência móvel, com navegação intuitiva e integração completa com backend via API REST.

**Funcionalidades Principais:**
- 📱 **Nativo Android** – Desenvolvido em Java com Android Studio
- 🔐 **Autenticação Segura** – Registo, Login e Logout via API
- 🖼️ **Gestão de Álbuns** – Criar, editar e organizar álbuns de fotos
- 🔍 **Pesquisa** – Encontrar utilizadores e álbuns públicos
- 📤 **Partilha** – Partilhar álbuns com outros utilizadores

---

## 🚀 Funcionalidades Principais

| Módulo | Funcionalidades |
| :--- | :--- |
| **🔐 Autenticação** | Registo de utilizador, Login, Logout, Recuperação de sessão. |
| **📸 Fotos** | Upload de fotos, Visualização em galeria, Delete de fotos. |
| **📁 Álbuns** | Criar álbum, Adicionar fotos, Editar nome/descrição. |
| **🔍 Pesquisa** | Buscar utilizadores, Buscar álbuns públicos. |
| **📤 Partilha** | Partilhar álbum com utilizadores específicos ou público. |

---

## 🏗️ Arquitectura e Tecnologias

A aplicação segue as boas práticas de desenvolvimento Android com arquitectura modular.

- **Linguagem:** Java
- **IDE:** Android Studio
- **Min SDK:** API 21 (Android 5.0+)
- **Target SDK:** API 33+ (Android 13+)
- **Comunicação:** Retrofit / Volley (API REST)
- **Armazenamento Local:** SharedPreferences / SQLite / Room
- **Imagens:** Glide / Picasso (carregamento de imagens)
- **Padrão:** MVC / MVVM (conforme implementação)

### 📊 Fluxo de Funcionamento

