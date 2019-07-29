### Package structure

<pre>
└── com
    └── example
        ├── data
        │   ├── daos - classes for abstract functions that access Room
        │   ├── entities - object representations of Room tables
        │   ├── local - helper classes for local data
        │   ├── models - kotlin data classes that do not resolve to an Entity
        │   ├── repositories - interfaces and implementation of repository pattern
        │   └── services - web services for Retrofit
        ├── di - Dagger dependency injection modules, and helpers
        ├── network - helper classes and extension functions for network communication
        └── ui - outward facing Views
</pre>

### Testing
- Tests are written with JUnit utilizing Robolectric for UI tests
- Unless testing interaction with Android System, <b>all tests should be written in `app/src/test`</b>.
    - This is to allow for CI checks, and better testing performance.
- Have a "test-first" mentality. There are a few helper functions to assist with making testing easier, see `app/src/shared/java/com/example/utils/`.

### Application patterns
- Uses MVVM architecture.
- Repository pattern for handle Model -> ViewModel -> View data flow.
- Abstract repositories into interfaces for better testing.

### Dependencies
- All library version are to be added to top-level `build.gradle`, and referenced in `app/build.gradle` via:
        
        implementation "some-dependency:${libraries.dependencyName}"
- No need to append "Version" to end of dependencyName, since these names are scoped to `libraries`.