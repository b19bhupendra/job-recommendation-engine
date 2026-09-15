# Job Recommendation Engine

A Spring Boot REST API that recommends jobs to candidates based on:

- Skills
- Years of experience
- Location
- Expected salary

The API ranks matching jobs using a weighted scoring system from 0 to 100.

## Tech Stack

- Java 17
- Spring Boot
- Spring Data MongoDB
- MongoDB
- Maven
- JUnit 5
- Lombok

## How to Run

### 1. Start MongoDB

Make sure MongoDB is running locally on:

`mongodb://127.0.0.1:27017`

### 2. Configure the Application

MongoDB connection is configured in:

`src/main/resources/application.properties`

### 3. Start the Application

Run:

```bash
mvn spring-boot:run
````

The application starts on:

`http://localhost:8080`

## API Endpoints

### Create Candidate

```http
POST /candidates
```

Example request:

```json
{
  "name": "John Doe",
  "skills": [
    "Java",
    "Spring Boot",
    "MongoDB"
  ],
  "yearsOfExperience": 4,
  "location": "Gurgaon",
  "expectedSalary": 1200000
}
```

### Create Job

```http
POST /jobs
```

Example request:

```json
{
  "title": "Senior Java Developer",
  "requiredSkills": [
    {
      "skill": "Java",
      "type": "MUST_HAVE"
    },
    {
      "skill": "Spring Boot",
      "type": "MUST_HAVE"
    },
    {
      "skill": "MongoDB",
      "type": "NICE_TO_HAVE"
    },
    {
      "skill": "Docker",
      "type": "NICE_TO_HAVE"
    }
  ],
  "minYearsExperience": 3,
  "location": "Gurgaon",
  "salaryRange": {
    "min": 1000000,
    "max": 1800000
  },
  "remoteAllowed": true
}
```

### Get Recommendations

```http
GET /recommendations/{candidateId}?limit=5
```

Example:

```http
GET /recommendations/6aa6ee54022d6f60af07ad59?limit=5
```

The recommendations are sorted by score in descending order.

Example response:

```json
[
  {
    "jobId": "6aa70ad2e6d2d83f5926348f",
    "title": "Senior Java Developer",
    "score": 95.0,
    "breakdown": {
      "skills": 45.0,
      "experience": 20.0,
      "location": 15.0,
      "salary": 15.0
    }
  }
]
```

The `limit` parameter controls the maximum number of recommendations returned.

`limit` must be greater than 0.

## Scoring Formula

The maximum score is 100.

| Category   | Maximum Score |
| ---------- | ------------: |
| Skills     |            50 |
| Experience |            20 |
| Location   |            15 |
| Salary     |            15 |
| **Total**  |       **100** |

### Skills - 50 Points

Skills are divided into:

* MUST_HAVE: 40 points
* NICE_TO_HAVE: 10 points

MUST_HAVE skills are treated as hard requirements.

If a candidate is missing any MUST_HAVE skill, the job is excluded.

NICE_TO_HAVE skills receive proportional points based on the number of matched skills.

Example:

* 2/2 MUST_HAVE matched = 40 points
* 1/2 NICE_TO_HAVE matched = 5 points
* Total = 45/50

### Experience - 20 Points

If the candidate meets or exceeds the required experience:

`20 points`

If the candidate has less experience, the score is reduced proportionally.

Experience below the requirement does not automatically exclude the job.

### Location - 15 Points

* Exact location match = 15 points
* Different location but remote is allowed = 10 points
* Different location and remote is not allowed = 0 points

Location matching is case-insensitive.

### Salary - 15 Points

The candidate's expected salary is treated as the minimum acceptable salary target.

* Expected salary is within the job salary range = 15 points
* Job maximum salary is below expected salary = 0 points
* Job minimum salary is above expected salary = 15 points

## Recommendation Process

For each candidate:

1. Find all available jobs.
2. Exclude jobs missing any MUST_HAVE skill.
3. Calculate the skill score.
4. Calculate the experience score.
5. Calculate the location score.
6. Calculate the salary score.
7. Add the scores to get the total score.
8. Sort recommendations by total score in descending order.
9. Return the top `limit` recommendations.

## Example Score

For a candidate with:

* Java
* Spring Boot
* MongoDB
* 4 years of experience
* Gurgaon location
* ₹12,00,000 expected salary

and a job requiring:

* Java - MUST_HAVE
* Spring Boot - MUST_HAVE
* MongoDB - NICE_TO_HAVE
* Docker - NICE_TO_HAVE
* 3 years minimum experience
* Gurgaon
* ₹10,00,000 - ₹18,00,000 salary range

The resulting score is:

| Category   |      Score |
| ---------- | ---------: |
| Skills     |      45/50 |
| Experience |      20/20 |
| Location   |      15/15 |
| Salary     |      15/15 |
| **Total**  | **95/100** |

## Assumptions

* Candidate `expectedSalary` is treated as a minimum acceptable salary target.
* Skills are matched using their string values.
* Location matching is case-insensitive.
* Experience below the required minimum reduces the score but does not exclude the job.
* A remote job receives a partial location score when the candidate's location differs.
* MUST_HAVE skills are hard requirements.
* Authentication and authorization are outside the scope of this assignment.
* No machine learning is used. Recommendations are based on deterministic scoring rules.

## Testing

The project contains unit tests covering:

* Missing MUST_HAVE skill excludes the job.
* No salary overlap produces a salary score of zero.
* Successful candidate/job match produces the expected score breakdown and total score.

Run the tests using:

```bash
mvn clean test
```

## AI Usage

AI assistance was used during development for:

* Discussing project structure and design.
* Reviewing the scoring approach.
* Explaining Spring dependency injection.
* Identifying and resolving implementation issues.
* Creating and reviewing unit-test scenarios.
* Reviewing API and validation logic.

The final scoring rules, assumptions, implementation decisions, and project structure were reviewed and adapted for this assignment.
