package com.example.t1ejercicios

import android.annotation.SuppressLint
import android.graphics.fonts.Font
import android.os.Bundle
import android.widget.Button
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.example.t1ejercicios.ui.theme.T1EjerciciosTheme
import kotlinx.coroutines.delay


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PerfilUsuarioIniciales()
        }
    }
}

@Composable
fun TituloSubtitulo() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Título",
            fontSize = 24.sp
        )
        Text(
            text = "Subtitulo",
            fontSize = 16.sp
        )
    }
}

@Composable
fun PerfilUsuario() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.ic_launcher_background),
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape),
            contentDescription = "Imagen avatar"
        )
        Spacer(Modifier.height(16.dp))
        Text(
            text = "Rafael Águila",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Desarrollador android",
            fontSize = 16.sp,
            color = Color.Gray
        )
    }
}

@Composable
fun ContadorConBotones() {
    var contador by remember { mutableIntStateOf(0) }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Contador: $contador",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(20.dp))
        Row {
            Button(onClick = { contador++ }) {
                Text("+")
            }
            Spacer(Modifier.padding(4.dp))
            Button(onClick = {
                if (contador > 0) {
                    contador--
                }
            }) {
                Text("-")
            }

        }
    }
}

@Composable
fun ListaSimple() {
    val tareas = listOf(
        "Aprender Compose",
        "Beber legia",
        "Practicar con layouts",
        "Ir a clase"
    )
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Mis tareas",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(4.dp),

            )
        LazyColumn {
            items(tareas) { tarea ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Text(
                        text = tarea,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}


@Composable
fun FormularioBasico() {
    var nombre by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var mensaje by remember { mutableStateOf("") }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Registro",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Introduce el nombre") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = correo,
            onValueChange = { correo = it },
            label = { Text("Introduce el correo") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                mensaje = "Hola $nombre"
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Enviar")
        }
        if (mensaje.isNotEmpty()) {
            Spacer(
                modifier = Modifier.height(16.dp)
            )
            Text(
                text = mensaje,
                color = Color.Magenta
            )
        }
    }
}

@Composable
fun AppNavegacion() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "inicio"
    ) {
        composable("inicio") { PantallaInicio(navController) }
        composable("perfil") { PantallaPerfil(navController) }
    }

}


@Composable
fun PantallaInicio(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Pantalla de inicio", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { navController.navigate("perfil") }
        ) {
            Text("Ir al perfil")
        }
    }

}


@Composable
fun PantallaPerfil(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Pantalla del perfil", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { navController.navigate("inicio") }
        ) {
            Text("Ir al inicio")
        }
    }

}

@Composable
fun Temporadizador() {
    var tiempo by remember { mutableStateOf(10) }
    var activo by remember { mutableStateOf(false) }

    LaunchedEffect(activo) {
        while (activo && tiempo > 0) {
            delay(1000)
            tiempo--
        }
        if (tiempo == 0) {
            activo = false
        }
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "$tiempo segundos",
            fontSize = 48.sp,
            color =
                if (tiempo <= 3)
                    Color.Red
                else
                    Color.Black
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxSize(),
        ) {
            Button(
                onClick = {
                    tiempo = 10
                    activo = true
                }
            ) {
                Text("Iniciar")
            }
            Button(
                onClick = {
                    activo = !activo
                }
            ) {
                Text(
                    text = if (activo) "Pausar" else "Iniciar"
                )
            }
        }
    }
}

@Composable
fun PerfilUsuarioIniciales() {
    val nombre = "Rafa"
    val apellido = "Aguila"
    val iniciales = "${nombre.firstOrNull() ?: ""}${apellido.firstOrNull() ?: ""}"
        .uppercase()
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .background(Color.Green),
            contentAlignment = Alignment.Center
        ){
            Text(
                text = iniciales,
                color = Color.Gray,
                fontSize = 36.sp
            )
        }
        Spacer(Modifier.height(16.dp))
        Text(
            text = "Rafael Águila",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Desarrollador android",
            fontSize = 16.sp,
            color = Color.Gray
        )
    }
}

