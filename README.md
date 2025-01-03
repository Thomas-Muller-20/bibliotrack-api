Here’s an updated and polished version in English:

---

# Bibliotrack-API

## **Spotless**

This project uses **Spotless** for automated code formatting, following the **Google Java Style Guide**.

### Commands for Spotless:

- **To apply formatting:**
  ```bash
  mvn spotless:apply
  ```

- **To check formatting:**
  ```bash
  mvn spotless:check
  ```

### Notes:
- Make sure to run `mvn clean install` before starting the application to clean previous builds, compile the code, run tests, and prepare all necessary artifacts for execution.
- To configure Spotless in your IDE, install the Spotless plugin and use the provided configuration.

---

## **CheckStyle-IDE**

This project also uses the **CheckStyle-IDE** linter. Ensure you install it as a plugin in your IDE and configure it using the file located at:

```
config/checkstyle.xml
```

---

### Why This Works:
1. **Clarity**: Clear instructions and separation of Spotless and CheckStyle-IDE tools.
2. **Readability**: Highlights important details with bold text.
3. **Consistency**: Keeps commands and paths in code blocks for better readability.