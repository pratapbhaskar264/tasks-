**SocialGuard – Viral Bot Control System**
    Features :
*         Real-time virality scoring using Redis
*         Bot detection & rate limiting
*         Horizontal cap (max 100 bot replies)
*         Vertical cap (max depth 20)
*         Cooldown mechanism (anti-spam)
*         Notification batching system
*         Scheduled job for summary notifications


**Tech Stack**
*     Java + Spring Boot
*     PostgreSQL
*     Redis
*     Docker

**PROJECT STRUCTURE :**

        socialguard
        │
        ├── controller
        │   ├── PostController
        │   └── CommentController
        │
        ├── service
        │   ├── PostService
        │   ├── CommentService
        │   ├── RedisService  
        │   └── NotificationService
        │
        ├── repository
        │   ├── PostRepository
        │   ├── CommentRepository
        │   ├── UserRepository
        │   └── BotRepository
        │
        ├── entity
        │   ├── User
        │   ├── Bot
        │   ├── Post
        │   └── Comment
        │
        ├── config
        │   ├── RedisConfig
        │   └── SchedulerConfig
        │
        ├── dto
        │   ├── PostRequestDTO
        │   ├── CommentRequestDTO
        │
        ├── exception
        │   ├── GlobalExceptionHandler
        │   └── CustomExceptions
        │
        └── SocialguardApplication

**APIs**
    ➤ Create Post
        POST /api/posts
    ➤ Add Comment
        POST /api/posts/{postId}/comments
    ➤ Like Post
        POST /api/posts/{postId}/like

**Redis Keys Used**
*     post:{id}:virality_score
*     post:{id}:bot_count
*     cooldown:bot_{id}:user_{id}
*     user:{id}:pending_notifs

**Key Design Decisions**
*     Redis INCR used for atomic operations → prevents race conditions
*     Cooldown via TTL → avoids repeated spam
*     Notification batching → reduces system load
*     Scheduler → processes queued notifications


**How to Run**

    1. Run Docker:
       docker compose up -d

    2. Run app:
      mvn spring-boot:run

    3. Test APIs via Postman