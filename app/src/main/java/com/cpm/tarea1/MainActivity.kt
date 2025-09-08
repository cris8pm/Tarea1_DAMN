package com.cpm.tarea1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cpm.tarea1.ui.theme.Tarea1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Tarea1Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController) }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(paddingValues)
        ) {
            composable("home") { HomeScreen(navController = navController) }
            composable("textfields") { TextFieldsScreen() }
            composable("buttons") { ButtonsScreen() }
            composable("selection_elements") { SelectionElementsScreen() }
            composable("lists") { ListsScreen() }
            composable("info_elements") { InfoElementsScreen() }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = Color.White
    ) {
        NavigationBarItem(
            selected = navController.currentDestination?.route == "home",
            onClick = { navController.navigate("home") {
                popUpTo(navController.graph.startDestinationId)
                launchSingleTop = true
            } },
            label = { Text("Inicio") },
            icon = { Icon(Icons.Default.Info, contentDescription = "Inicio") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.White,
                unselectedIconColor = Color.White.copy(alpha = 0.6f),
                selectedTextColor = Color.White,
                unselectedTextColor = Color.White.copy(alpha = 0.6f)
            )
        )
    }
}

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Explora los elementos básicos de la interfaz de usuario (UI)",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botones de navegación para cada "fragment"
        NavigationCard(
            title = "Text Fields",
            description = "Campos de texto para entrada de usuario.",
            onClick = { navController.navigate("textfields") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        NavigationCard(
            title = "Botones",
            description = "Acciones interactivas con botones e imágenes.",
            onClick = { navController.navigate("buttons") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        NavigationCard(
            title = "Elementos de Selección",
            description = "Opciones para seleccionar valores.",
            onClick = { navController.navigate("selection_elements") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        NavigationCard(
            title = "Listas",
            description = "Muestra colecciones de elementos.",
            onClick = { navController.navigate("lists") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        NavigationCard(
            title = "Elementos de Información",
            description = "Muestra datos al usuario.",
            onClick = { navController.navigate("info_elements") }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationCard(title: String, description: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        onClick = onClick,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Text(
                text = description,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f)
            )
        }
    }
}


// --- "Fragments" o Composable con Jetpack Compose---

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldsScreen() {
    var textValue by remember { mutableStateOf("") }
    var passwordValue by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Text Fields (EditText)",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(
            text = "Permiten al usuario ingresar texto, números u otros datos. Son fundamentales para formularios.",
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = textValue,
            onValueChange = { textValue = it },
            label = { Text("Nombre de usuario") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = passwordValue,
            onValueChange = { passwordValue = it },
            label = { Text("Contraseña") },
            visualTransformation = androidx.compose.ui.text.input.PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        )
        Text("Texto ingresado: $textValue", modifier = Modifier.padding(bottom = 8.dp))
        Text("Contraseña (oculta): ${"*".repeat(passwordValue.length)}")
    }
}

@Composable
fun ButtonsScreen() {
    var buttonClickedCount by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Botones (Button, ImageButton)",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(
            text = "Permiten al usuario realizar acciones al tocarlos. Pueden ser de texto o incluir iconos.",
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Button(
            onClick = { buttonClickedCount++ },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        ) {
            Text("Haz clic aquí")
        }

        ImageButton(
            onClick = { buttonClickedCount += 2 },
            modifier = Modifier.size(64.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text("Clics: $buttonClickedCount")
    }
}

@Composable
fun ImageButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
    IconButton(onClick = onClick, modifier = modifier) {
        Image(
            painter = painterResource(id = R.drawable.escom_logo),
            contentDescription = "Botón de imagen",
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun SelectionElementsScreen() {
    var isChecked by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf("Opción 1") }
    var isSwitchOn by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Elementos de Selección (CheckBox, RadioButton, Switch)",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(
            text = "Ofrecen al usuario opciones para elegir, ya sea múltiples o una sola, o para activar/desactivar funciones.",
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = isChecked,
                onCheckedChange = { isChecked = it }
            )
            Text("Aceptar términos y condiciones")
        }
        Text("Checkbox: ${if (isChecked) "Activado" else "Desactivado"}", modifier = Modifier.padding(bottom = 16.dp))

        Spacer(modifier = Modifier.height(8.dp))

        Text("Selecciona una opción:")
        Row {
            RadioButton(selected = selectedOption == "Opción 1", onClick = { selectedOption = "Opción 1" })
            Text("Opción 1", modifier = Modifier.align(Alignment.CenterVertically))
            Spacer(modifier = Modifier.width(16.dp))
            RadioButton(selected = selectedOption == "Opción 2", onClick = { selectedOption = "Opción 2" })
            Text("Opción 2", modifier = Modifier.align(Alignment.CenterVertically))
        }
        Text("Opción seleccionada: $selectedOption", modifier = Modifier.padding(bottom = 16.dp))

        Spacer(modifier = Modifier.height(8.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Activar modo oscuro")
            Spacer(modifier = Modifier.width(8.dp))
            Switch(checked = isSwitchOn, onCheckedChange = { isSwitchOn = it })
        }
        Text("Switch: ${if (isSwitchOn) "Activado" else "Desactivado"}")
    }
}

@Composable
fun ListsScreen() {
    val items = remember { listOf("Manzana", "Plátano", "Naranja", "Uva", "Fresa", "Mango", "Piña", "Cereza") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Listas (RecyclerView o ListView)",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(
            text = "Muestran grandes cantidades de datos de manera eficiente, permitiendo el desplazamiento.",
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(items) { item ->
                ListItem(text = item)
            }
        }
    }
}

@Composable
fun ListItem(text: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(16.dp),
            fontSize = 18.sp
        )
    }
}

@Composable
fun InfoElementsScreen() {
    var progress by remember { mutableStateOf(0.0f) }

    // Para simular un progreso con un abarra de carga
    LaunchedEffect(Unit) {
        while (progress < 1.0f) {
            kotlinx.coroutines.delay(100) // Esperar 100ms
            progress += 0.01f
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Elementos de Información (TextView, ImageView, ProgressBar)",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(
            text = "Utilizados para mostrar texto estático, imágenes o el estado de una operación al usuario.",
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // TextView
        Text(
            text = "Este es un TextView, muestra texto al usuario. Puedes cambiar su estilo y contenido.",
            fontSize = 16.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // ImageView
        Image(
            painter = painterResource(id = R.drawable.android_logo),
            contentDescription = "Logo de Android",
            modifier = Modifier
                .size(120.dp)
                .padding(bottom = 16.dp)
        )

        // ProgressBar
        LinearProgressIndicator(
            progress = progress,
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )
        Text("Progreso de carga: ${(progress * 100).toInt()}%")
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Tarea1Theme {
        MainScreen()
    }
}