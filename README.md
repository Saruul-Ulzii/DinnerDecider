# Android development - Kotlin

Android development - Kotlin

Developed:
- **Dinner decider** app
- **CV Builder** app
  > LinkedIn profile
- **Walmart** clone app
  > Sign in/Sign up/Product list/Product details/Reset password

```mermaid
graph TD
A[Sign In] --> |Valid Credentials| B[List Products]
A -->|Invalid Credentials| A

B --> |Select Product| C[Product Details]
B --> |Logout| A[Sign In]

A --> |Forgot Password| D[Reset Password]
D --> |Password Reset| A
D --> |Cancel| A

A --> |No Account| E[Sign Up]
E --> |SignUp Success| B

E --> |Cancel| A
```
```mermaid
classDiagram
  class User {
    +username: String
    +password: String
    +email: String
    +signIn(): Boolean
    +signUp(): Boolean
    +resetPassword(): Boolean
  }

  class Product {
    +productId: String
    +name: String
    +price: Double
    +description: String
  }

  class ShoppingCart {
    +items: List<Product>
    +addItem(product: Product): void
    +removeItem(product: Product): void
    +checkout(): void
  }

  class App {
    +signIn(user: User): void
    +signUp(user: User): void
    +resetPassword(user: User): void
    +listProducts(): List<Product>
    +viewProductDetails(product: Product): void
  }

  User --> App
  Product --> App
  ShoppingCart --> App
```
```mermaid
sequenceDiagram
  participant User
  participant App
  participant Database

  User ->> App: Sign In
  App ->> Database: Validate Credentials
  Database -->> App: Valid Credentials
  App -->> User: Sign In Successful

  User ->> App: Sign Up
  App ->> Database: Create User
  Database -->> App: User Created
  App -->> User: Sign Up Successful

  User ->> App: Forgot Password
  App ->> Database: Reset Password
  Database -->> App: Password Reset
  App -->> User: Password Reset Successful

  User ->> App: List Products
  App ->> Database: Retrieve Product List
  Database -->> App: Product List
  App -->> User: Display Product List

  User ->> App: Select Product
  App ->> Database: Retrieve Product Details
  Database -->> App: Product Details
  App -->> User: Display Product Details
```
- **Quiz app** 
  > Full dynamic and single/multi/true false choice
```mermaid
classDiagram
class QuizApp {
  +mainScreen: MainScreen
  +quizScreen: QuizScreen
  +resultScreen: ResultScreen
}

class MainScreen {
  +startQuiz(): void
}

class QuizScreen {
  +displayQuestion(): void
  +submitAnswer(answer: String): void
}

class ResultScreen {
  +showResult(score: Number): void
}

QuizApp --> MainScreen
QuizApp --> QuizScreen
QuizApp --> ResultScreen
```
```mermaid
classDiagram
  class MainScreen {
    +startQuiz(): void
  }

  class QuizScreen {
    +displayQuestion(): void
    +submitAnswer(answer: String): void
  }

  class ResultScreen {
    +showResult(score: Number): void
  }

  MainScreen --> QuizScreen: startQuiz()
  QuizScreen --> ResultScreen: submitAnswer()
```
```mermaid
sequenceDiagram
  participant User
  participant QuizApp
  participant MainScreen
  participant QuizScreen
  participant ResultScreen

  User ->> MainScreen: Start Quiz
  MainScreen ->> QuizApp: startQuiz()
  QuizApp ->> QuizScreen: displayQuestion()

  User ->> QuizScreen: Submit Answer
  QuizScreen ->> QuizApp: submitAnswer(answer)
  QuizApp ->> ResultScreen: showResult(score)
  ResultScreen -->> User: Display Result
```
