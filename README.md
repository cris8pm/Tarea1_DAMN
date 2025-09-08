🚀 Tarea1: Elementos de Interfaz de Usuario
Esta primer tarea es una aplcación de demostración desarrollada en Android con Jetpack Compose y Kotlin. Su propósito es explorar y mostrar el uso práctico de diferentes elementos de la interfaz de usuario (UI) de Android de un manera interactiva.
🌟 Características Principales
La aplicación Tarea1 presenta una MainActivity principal que actúa como un contenedor para diversas secciones, cada una enfocada en un tipo específico de componente de UI. La navegación entre estas secciones se realiza a través de un menú intuitivo.
Estructura de la Aplicación
La aplicación está organizada en una Activity principal que simula una navegación entre 5 "pantallas" (composables en Jetpack Compose), cada una explicando y demostrando un elemento de interfaz diferente:
Text Fields (EditText): Campos de entrada de texto.
Botones (Button, ImageButton): Elementos interactivos para desencadenar acciones.
Elementos de Selección (CheckBox, RadioButton, Switch): Componentes para que el usuario elija opciones.
Listas (RecyclerView o ListView simulado con LazyColumn): Para mostrar colecciones de datos.
Elementos de Información (TextView, ImageView, ProgressBar): Para presentar datos estáticos, imágenes o el progreso de tareas.
Navegación
El proyecto incluye:
Un menú de navegación principal (HomeScreen) que permite acceder a cada una de las secciones de demostración.
Cada "fragment" (pantalla composable) es accesible directamente desde el menú principal.
Contenido de Cada Sección
Cada pantalla de demostración está diseñada para ser educativa e interactiva, incluyendo:
📝 Título descriptivo: Un encabezado claro del elemento de UI.
🎨 Ejemplos visuales: Implementaciones funcionales del elemento.
💡 Explicación breve: Un resumen conciso de su propósito (máximo 2-3 líneas).
⚡ Demostración interactiva: Los usuarios pueden interactuar directamente con el componente para entender su funcionamiento.

🛠️ Tecnologías Utilizadas
Android Studio
Kotlin como lenguaje de programación.
Jetpack Compose para la construcción de la interfaz de usuario.
Jetpack Navigation Compose para la gestión de la navegación.
🚀 Cómo Ejecutar el Proyecto
Sigue estos pasos para poner en marcha el proyecto en tu entorno de desarrollo:

git clone https://github.com/cris8pm/Tarea1_DAMN/
cd Tarea1

Abre el proyecto en Android Studio:
Inicia Android Studio.
Selecciona File > Open y navega hasta la carpeta Tarea1 que clonaste.
Sincroniza Gradle:
Una vez que el proyecto se abra, Android Studio debería iniciar automáticamente la sincronización de Gradle. Si no es así, haz clic en File > Sync Project with Gradle Files o en el icono de sincronización en la barra de herramientas.
Asegúrate de las dependencias y configuración del compilador Compose:
Verifica tu archivo build.gradle.kts (Module: app) para asegurarte de que las dependencias de Jetpack Compose y el plugin del compilador de Compose estén correctamente configurados, especialmente id("org.jetbrains.kotlin.plugin.compose") en la sección de plugins y kotlinCompilerExtensionVersion en composeOptions.
Ejecuta la aplicación:
Conecta un dispositivo Android físico a tu computadora o inicia un emulador de Android.
Selecciona tu dispositivo o emulador en el menú desplegable de Android Studio.
Haz clic en el botón Run 'app' (el icono de play verde) en la barra de herramientas.
