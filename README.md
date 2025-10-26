# HouseArmony

**Gestiona tu hogar de manera eficiente**

Una aplicación web full-stack moderna para administrar tareas domésticas, facturas y responsabilidades grupales del hogar.

---

## Características

- **Gestión de Perfiles**: Crea y administra perfiles de miembros del hogar
- **Sistema de Grupos**: Organiza tareas por grupos familiares
- **Tareas Domésticas**: Asigna y rastrea tareas del hogar
- **Limpieza General**: Planifica tareas de limpieza recurrentes
- **Gestión de Facturas**: Administra y divide facturas del hogar
- **Temas Dark/Light**: Interfaz adaptable a tus preferencias
- **Responsive**: Funciona en desktop, tablet y móvil

---

## Stack Tecnológico

### Frontend
- **React 18.3** + **TypeScript 5.5**
- **Vite** - Build tool ultrarrápido
- **Tailwind CSS** + **shadcn/ui** - UI moderna y accesible
- **React Router** - Navegación
- **Zustand** - State management
- **React Query** - Data fetching y caché

### Backend
- **Spring Boot 3.4** + **Java 17**
- **Spring Data JPA** + **Hibernate**
- **PostgreSQL** - Base de datos
- **Gradle** - Build automation

---

## Desarrollo Local

### Prerequisitos

- **Node.js** 18+ y npm
- **Java** 17+
- **PostgreSQL** 12+
- **Gradle** 8+ (incluido via wrapper)

### Configuración de Base de Datos

1. Crea una base de datos PostgreSQL:
   ```bash
   createdb task_manager
   ```

2. Configura las credenciales en `backend/src/main/resources/application-dev.properties`

### Backend Setup

```bash
cd backend

# Build y run con Gradle
./gradlew bootRun

# O usando tu IDE (IntelliJ/Eclipse)
# Abre backend/ como proyecto Gradle y ejecuta HouseharmonyApplication
```

El backend estará disponible en `http://localhost:8080`

### Frontend Setup

```bash
cd frontend

# Instalar dependencias
npm install

# Copiar variables de entorno
cp .env.example .env.development

# Iniciar dev server
npm run dev
```

El frontend estará disponible en `http://localhost:5173`

---

## Deployment

Ver [DEPLOYMENT.md](./DEPLOYMENT.md) para instrucciones detalladas de cómo desplegar en:

- **Frontend**: Vercel (gratis)
- **Backend**: Railway (gratis)
- **Database**: Neon PostgreSQL (gratis)

---

## Estructura del Proyecto

```
housearmony/
├── frontend/                 # Aplicación React
│   ├── src/
│   │   ├── components/      # Componentes React
│   │   ├── pages/           # Páginas/rutas
│   │   ├── services/        # API services
│   │   ├── stores/          # Zustand stores
│   │   ├── hooks/           # Custom hooks
│   │   └── types/           # TypeScript types
│   └── package.json
│
├── backend/                  # API Spring Boot
│   ├── src/main/java/
│   │   └── com/meyb/househarmony/
│   │       ├── controller/  # REST controllers
│   │       ├── service/     # Business logic
│   │       ├── repository/  # Data access
│   │       ├── entity/      # JPA entities
│   │       └── config/      # Configuration
│   └── build.gradle
│
└── DEPLOYMENT.md            # Guía de deployment
```

---

## API Endpoints

### Profiles
- `GET    /api/profiles`     - Listar todos los perfiles
- `GET    /api/profiles/{id}` - Obtener perfil por ID
- `POST   /api/profiles`     - Crear nuevo perfil
- `PUT    /api/profiles/{id}` - Actualizar perfil
- `DELETE /api/profiles/{id}` - Eliminar perfil

---

## Scripts Disponibles

### Frontend
```bash
npm run dev       # Dev server con hot reload
npm run build     # Build para producción
npm run preview   # Preview del build de producción
npm run lint      # Linter
```

### Backend
```bash
./gradlew bootRun           # Run application
./gradlew build             # Build JAR
./gradlew test              # Run tests
./gradlew clean             # Clean build artifacts
```

---

## Variables de Entorno

### Frontend (.env)
```bash
VITE_API_BASE_URL=http://localhost:8080
```

### Backend (application-dev.properties o env vars)
```bash
SPRING_PROFILES_ACTIVE=dev
DATABASE_URL=jdbc:postgresql://localhost:5432/task_manager
CORS_ALLOWED_ORIGINS=http://localhost:5173
```

Ver `.env.example` en cada directorio para más detalles.

---

## Contribuir

1. Fork el proyecto
2. Crea tu feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push al branch (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

---

## Roadmap

- [x] Sistema de perfiles
- [x] Configuración de grupos
- [ ] CRUD de tareas
- [ ] CRUD de facturas
- [ ] Sistema de notificaciones
- [ ] Autenticación y autorización
- [ ] Dashboard con estadísticas
- [ ] Exportar reportes (PDF/Excel)
- [ ] App móvil (React Native)

---

## Licencia

Este proyecto es de código abierto y está disponible bajo la licencia MIT.

---

## Autor

**Marlon** - [GitHub](https://github.com/marlon19991)

---

## Soporte

Si tienes problemas o preguntas:

1. Revisa la [Guía de Deployment](./DEPLOYMENT.md)
2. Abre un [Issue](https://github.com/marlon19991/housearmony/issues)
3. Consulta la documentación de las tecnologías utilizadas
