# StudyFlow — Master Product Specification & 231-Feature Implementation Matrix

> **Document Type:** Master Feature Inventory, Data Mapping, and Delivery Plan  
> **Target Release:** November 27, 2026 (Hackathon Showcase & Commercial SaaS Roadmap)  
> **Total Feature Groups:** 55 Groups (41 Core App + 14 Visual Editor, Motion, Layers & Aceternity UI Groups)  
> **Total Granular Features:** 231 Individual Features (162 Core App + 69 Visual Editor, Motion, Layers & Aceternity UI Features)  

---

## Executive Summary & Feature Group Distribution

```
┌──────────────────────────────────────────────────────────────────────────────┐
│                    STUDYFLOW COMPLETE FEATURE DISTRIBUTION                   │
│                                                                              │
│   1. OWNER / ADMIN PORTAL           : 11 Groups |  54 Individual Features    │
│   2. TEACHER PORTAL                 : 11 Groups |  41 Individual Features    │
│   3. STUDENT & PARENT PORTAL        : 12 Groups |  43 Individual Features    │
│   4. CROSS-CUTTING ARCHITECTURE     :  7 Groups |  24 Individual Features    │
│   5. WEBSITE BUILDER, MOTION & AUTH : 14 Groups |  69 Individual Features    │
│  ──────────────────────────────────────────────────────────────────────────  │
│   TOTAL                             : 55 Groups | 231 Individual Features    │
└──────────────────────────────────────────────────────────────────────────────┘
```

### Implementation Execution Tiers
* **Tier 1 (🔥 Core Live End-to-End):** Functional across React, Spring Boot, MySQL, Groq AI, and Razorpay for the November 27 showcase.
* **Tier 2 (⚡ Interactive UI + Seed Data):** Clickable, visual UI screens populated with realistic seed data (*"Apex Science Academy"*) allowing full user journeys without dead links.
* **Tier 3 (🚀 Enterprise Roadmap):** Deep operational workflows scheduled for the post-hackathon commercial release.

---

## Part 1: Owner / Admin Portal (11 Groups · 54 Features)

### Group 1: Executive Dashboard & Analytics
* **ADM-01-01 (⚡ Total Students Counter):** Real-time aggregation of active enrolled students, categorized by standard/course.
* **ADM-01-02 (⚡ Faculty Roster Widget):** Active teacher count with active class allocations for the day.
* **ADM-01-03 (⚡ Monthly Revenue Metrics):** Total fees collected this month vs. previous month with growth percentage.
* **ADM-01-04 (⚡ Pending Fee Defaulter Metric):** Aggregated uncollected fees with count of overdue student accounts.
* **ADM-01-05 (⚡ Attendance Trend Line Chart):** 30-day graphical visualization of institute-wide student attendance percentage.
* **ADM-01-06 (⚡ Batch Capacity Gauge):** Visual bar cards showing student enrollment vs. maximum room capacity for each batch.

### Group 2: Student Management & Admissions
* **ADM-02-01 (🔥 Admission Lead Pipeline):** Kanban board tracking leads through stages: `NEW` $\rightarrow$ `CONTACTED` $\rightarrow$ `DEMO_ATTENDED` $\rightarrow$ `ENROLLED`.
* **ADM-02-02 (🔥 Public Website Enquiry Tracker):** Live table capturing incoming enquiries from the public website with 1-click WhatsApp call action.
* **ADM-02-03 (🔥 Student Registration Form):** Multi-step modal to register a student with parent details, school, target exam, and batch assignment.
* **ADM-02-04 (⚡ Digital Student ID Card Generator):** Generates printable/downloadable student ID cards with QR code for attendance scanning.
* **ADM-02-05 (🔥 Comprehensive Student Profile View):** 360-degree modal displaying academic history, enrolled batches, attendance percentage, and fee receipts.
* **ADM-02-06 (⚡ Alumni Records Archive):** Past year pass-outs categorized by year and colleges cracked (IIT, AIIMS, NIT, DU).

### Group 3: Teacher Management & Operations
* **ADM-03-01 (🔥 Teacher Directory):** Roster of all faculty members with subject specialization, degrees, and phone numbers.
* **ADM-03-02 (⚡ Teacher Onboarding Form):** Add new faculty, assign subjects (Physics, Chemistry, Maths), and assign batches.
* **ADM-03-03 (⚡ Salary & Payout Ledger):** Monthly payroll calculation based on fixed salary or hourly lecture rates.
* **ADM-03-04 (⚡ Teacher Attendance & Leave Ledger):** Tracks daily teacher presence, leave balances, and substitute teacher assignments.
* **ADM-03-05 (⚡ Workload & Lecture Hours Monitor):** Visual hours-per-week bar charts preventing teacher burnout and scheduling clashes.

### Group 4: Batch & Course Configuration
* **ADM-04-01 (🔥 Course Catalog Setup):** Create courses (e.g., "Class 11 JEE Pinnacle", "Class 10 CBSE Foundation").
* **ADM-04-02 (🔥 Batch Timing Slots):** Schedule daily batch hours (e.g., "Mon-Wed-Fri, 4:00 PM – 6:00 PM").
* **ADM-04-03 (🔥 Subject & Teacher Mapping):** Allocate specific teachers to specific subjects within a batch.
* **ADM-04-04 (🔥 Batch Capacity Limit Enforcer):** Strict seat cap logic that flags when a batch is full.
* **ADM-04-05 (🔥 Classroom & Room Allocation):** Assign physical classrooms (Room 101, Lab B) to batches.

### Group 5: Timetable & Scheduling
* **ADM-05-01 (⚡ Weekly Master Schedule Grid):** Visual matrix displaying all classrooms and batches across 7 days.
* **ADM-05-02 (⚡ Clash Detection Engine):** Automatic validation preventing a teacher or room from being assigned to two simultaneous classes.
* **ADM-05-03 (⚡ Dynamic Rescheduling & Cancellation):** 1-click class cancellation with automated WhatsApp alert to enrolled students.
* **ADM-05-04 (⚡ Holiday & Exam Schedule Overlay):** Blocks out academic holidays and special revision schedules.

### Group 6: Fees & Finance Management
* **ADM-06-01 (🔥 Dynamic Fee Structure Builder):** Configure one-time, monthly, or installment fee plans per course.
* **ADM-06-02 (🔥 Installment Due Dates Scheduler):** Automatically creates installment milestones (e.g., 3 installments of ₹10,000).
* **ADM-06-03 (🔥 Online Payment Gateway [Razorpay]):** Generates online payment links and accepts UPI, credit/debit cards, and net banking.
* **ADM-06-04 (🔥 Automated Digital Receipt Generator):** Generates GST-compliant downloadable PDF receipts with transaction IDs upon payment.
* **ADM-06-05 (⚡ Automated WhatsApp Payment Reminders):** 1-click trigger to send due fee reminders to parents on WhatsApp.
* **ADM-06-06 (⚡ Scholarship & Discount Rules):** Apply merit-based discounts (% or flat amount) with admin approval tracking.
* **ADM-06-07 (⚡ Operational Expense Tracker):** Log rent, electricity, printing, and equipment expenses.
* **ADM-06-08 (⚡ Profit & Loss Monthly Report):** Visual comparison of total fees collected vs. operational expenses.

### Group 7: Announcements & Communication
* **ADM-07-01 (⚡ Institute Notice Board):** Publish rich-text announcements pinned to the student and teacher portals.
* **ADM-07-02 (⚡ WhatsApp Broadcast Trigger):** Send formatted batch announcements directly to student/parent phone numbers.
* **ADM-07-03 (🔥 Automated Email Dispatcher [JavaMailSender]):** Sends admission confirmations and payment receipts via SMTP.
* **ADM-07-04 (⚡ Emergency Alert Banner):** High-priority red banner for sudden weather closures or urgent schedule shifts.

### Group 8: Reports & Academic Analytics
* **ADM-08-01 (⚡ Attendance Analytics Report):** Batch-wise and month-wise attendance percentage tables with CSV export.
* **ADM-08-02 (⚡ Result Distribution Curve):** Visual bell curve showing test performance across batches.
* **ADM-08-03 (🔥 Defaulter Master List):** Filterable table of all students with overdue fees sorted by days pending.
* **ADM-08-04 (⚡ Teacher Performance Rating):** Anonymous student ratings and average test score progression per teacher.
* **ADM-08-05 (⚡ Year-over-Year Admission Growth):** Historical enrollment trends to measure marketing ROI.

### Group 9: Roles & Granular Permissions
* **ADM-09-01 (🔥 Super Admin / Owner Role):** Unrestricted access to financial ledger, settings, and teacher salaries.
* **ADM-09-02 (⚡ Receptionist / Front Desk Role):** Restricted access: Can view and add leads and register students, but cannot see finances.
* **ADM-09-03 (⚡ Accountant Role):** Restricted access: Can manage fee receipts and expenses, but cannot edit batches or websites.
* **ADM-09-04 (🔥 Audit Log Tracker):** Security audit log recording who modified fees, batch timings, or student records.

### Group 10: Institute Settings & White-Labeling
* **ADM-10-01 (🔥 Institute Profile & Branding):** Set coaching name, logo, address, tagline, and brand color palette.
* **ADM-10-02 (🔥 Custom Subdomain / Slug Manager):** Claim and manage `<slug>.studyflow.in`.
* **ADM-10-03 (⚡ Academic Calendar Configuration):** Define term dates, assessment cycles, and weekly off-days.
* **ADM-10-04 (⚡ Database Backup & Data Export):** 1-click export of all student, batch, and fee records to JSON/CSV.
* **ADM-10-05 (⚡ Multi-Branch Switcher):** Manage multiple coaching branches from a single unified owner login.

### Group 11: Marketing & Public Website Engine
* **ADM-11-01 (🔥 Groq AI Pamphlet-to-Website Scanner):** Upload photo of paper brochure $\rightarrow$ `Qwen 3.8 27B` auto-generates website.
* **ADM-11-02 (🔥 Modular Click-to-Activate Canvas):** Checkbox panel toggling Hero, Batches, Toppers, Fees, and Contact modules.
* **ADM-11-03 (🔥 Live Dual-Screen Preview):** Real-time synchronized desktop and mobile website preview during editing.
* **ADM-11-04 (🔥 Demo Class Lead Capture Modal):** High-converting lead form routing enquiries directly to WhatsApp.
* **ADM-11-05 (🔥 Toppers / Hall of Fame Showcase):** Cards displaying student photos, ranks, exam cracked, and test scores.
* **ADM-11-06 (⚡ Printable A4 Admission Poster Generator):** Generates high-res printable poster with a QR code linking to demo booking.

---

## Part 2: Teacher Portal (11 Groups · 41 Features)

### Group 12: My Classes & Daily Schedule
* **TCH-12-01 (🔥 Today's Schedule Overview):** Chronological list of today's assigned lectures with start/end countdown.
* **TCH-12-02 (🔥 Batch Student Roster):** View all enrolled students in a specific batch with contact info.
* **TCH-12-03 (⚡ Weekly Teaching Matrix):** Weekly visual calendar showing upcoming lecture topics.
* **TCH-12-04 (⚡ Quick Substitute Flag):** Mark a lecture as covered by a substitute faculty member.

### Group 13: Attendance Tracking
* **TCH-13-01 (🔥 1-Click Quick Attendance Toggle):** Rapid `Present` / `Absent` / `Late` toggles per student.
* **TCH-13-02 (🔥 Live Database & Student Sync):** Instant sync to MySQL that updates the student's dashboard in real time.
* **TCH-13-03 (⚡ Bulk "Mark All Present" Action):** 1-click button to mark the entire batch present, then toggle absentees.
* **TCH-13-04 (⚡ Auto-Notify Absentee Parents):** Automatically queues WhatsApp/SMS alerts to parents of absent students.

### Group 14: Study Material & Content Management
* **TCH-14-01 (🔥 PDF Chapter Notes Uploader):** Upload syllabus notes and study resources directly linked to a batch.
* **TCH-14-02 (⚡ Video Lecture Embedder):** Embed YouTube Unlisted or Google Drive lecture links.
* **TCH-14-03 (⚡ Syllabus Progress Tracker):** Visual progress bar tracking completed chapters vs. remaining topics.
* **TCH-14-04 (⚡ File Access Permissions):** Toggle files between public lead-magnets and enrolled-student-only.

### Group 15: Homework & Assignments
* **TCH-15-01 (⚡ Assignment Creator):** Set homework title, instructions, attached PDF, and submission deadline.
* **TCH-15-02 (⚡ Submission Inbox):** List of students who submitted on time vs. pending defaulters.
* **TCH-15-03 (⚡ Grading & Feedback Input):** Enter marks/grades and write qualitative feedback on submitted work.
* **TCH-15-04 (⚡ Solution Key Publisher):** Automatically publishes solution PDFs once the deadline passes.

### Group 16: Tests & Quizzes
* **TCH-16-01 (⚡ MCQ Quiz Builder):** Create timed 4-option questions with correct answer keys.
* **TCH-16-02 (⚡ Question Bank Repository):** Reusable pool of questions tagged by subject and difficulty.
* **TCH-16-03 (⚡ Subjective Test Scheduler):** Announce offline written test dates, total marks, and topics tested.
* **TCH-16-04 (⚡ Timed Lockdown Test Scheduler):** Configure auto-start and auto-submit test windows.
* **TCH-16-05 (⚡ Batch Average & Difficulty Breakdown):** See which questions were missed by the most students.

### Group 17: Marks Entry & Results
* **TCH-17-01 (⚡ Grid Marksheet Entry):** Spreadsheet-style grid to rapidly enter marks for an entire batch.
* **TCH-17-02 (⚡ Auto-Rank & Percentile Generator):** Calculates batch ranks and percentage scores instantly.
* **TCH-17-03 (⚡ Report Card Publishing Trigger):** Publishes marks to the Student/Parent view with one click.
* **TCH-17-04 (⚡ Highest / Lowest / Median Stats):** Statistical overview of batch performance on each test.

### Group 18: Live Classes & Virtual Teaching
* **TCH-18-01 (⚡ Instant Live Class Launcher):** Launch scheduled Zoom or Google Meet links directly from the class card.
* **TCH-18-02 (⚡ WebRTC Whiteboard Placeholder):** In-browser drawing and formula writing board.
* **TCH-18-03 (⚡ Lecture Recording Archive):** Paste recording URLs available to absent students.
* **TCH-18-04 (⚡ Auto-Attendance on Live Class Join):** Logs timestamp when a student clicks into the meeting link.

### Group 19: Doubt Solving & Student Discussion
* **TCH-19-01 (⚡ Doubt Queue Inbox):** Incoming questions from students filtered by batch and topic.
* **TCH-19-02 (⚡ Rich-Text & Image Reply):** Teacher replies with typed explanations or uploaded handwritten solution photos.
* **TCH-19-03 (⚡ Public Forum Toggle):** Convert a good question and answer into a public FAQ for the entire batch.

### Group 20: Student Insights & Learning Analytics
* **TCH-20-01 (⚡ Topic Weak-Spot Detector):** Flags concepts where over 40% of the batch scored below passing.
* **TCH-20-02 (⚡ Individual Progress Trajectory):** Visual line chart tracking a student's score over the last 5 tests.
* **TCH-20-03 (⚡ At-Risk Student Warning System):** Flags students with attendance $<75\%$ or declining test grades.

### Group 21: Leaves, Payslips & Schedule Workload
* **TCH-21-01 (⚡ Monthly Payslip Viewer):** View itemized earnings, hours taught, and base compensation.
* **TCH-21-02 (⚡ Leave Application Request):** Submit leave requests with reason and dates to the institute owner.
* **TCH-21-03 (⚡ Workload Hour Balance):** Shows completed hours this month vs. contracted target hours.

---

## Part 3: Student & Parent Portal (12 Groups · 43 Features)

### Group 22: Student Dashboard
* **STU-22-01 (🔥 Today's Class Cards):** Schedule cards showing upcoming classes, teacher names, and room numbers.
* **STU-22-02 (🔥 Real-Time Attendance Badge):** Live percentage display showing overall attendance health.
* **STU-22-03 (🔥 Pending Homework Alert):** Card highlighting assignments due within 24 hours.
* **STU-22-04 (🔥 Notice Board Feed):** Recent announcements from the institute management.

### Group 23: Timetable & Calendar
* **STU-23-01 (⚡ Weekly Class Calendar):** Visual weekly calendar displaying regular classes, tests, and holidays.
* **STU-23-02 (⚡ Class Schedule Push Reminders):** Reminder notification 30 minutes before class commences.
* **STU-23-03 (⚡ Room / Batch Change Alerts):** Notice of room or schedule adjustments.

### Group 24: Study Material & Resource Locker
* **STU-24-01 (🔥 Chapter-Wise Notes Viewer):** Browse and open PDF study notes uploaded by faculty.
* **STU-24-02 (🔥 Download Locker):** Download offline PDF study packages directly to device.
* **STU-24-03 (⚡ Video Lecture Player):** Watch recorded and supplemental video explanations.
* **STU-24-04 (⚡ Previous Year Questions [PYQs]):** Access curated exam question papers with answer keys.

### Group 25: Homework & Assignments
* **STU-25-01 (⚡ Homework Submission Portal):** Upload photos of handwritten homework or submit PDF answers.
* **STU-25-02 (⚡ Grade & Feedback Card):** View teacher scores, corrections, and personalized advice.
* **STU-25-03 (⚡ Assignment History Ledger):** Historical record of all submitted, graded, and overdue tasks.

### Group 26: Online Tests & Quizzes
* **STU-26-01 (⚡ Interactive Test Interface):** Timed MCQ interface with question palette and progress counter.
* **STU-26-02 (⚡ Instant Score Generation):** Immediate results upon test submission with total score.
* **STU-26-03 (⚡ Answer Key & Explanation Review):** Step-by-step solutions for incorrect questions.
* **STU-26-04 (⚡ Topic-Wise Accuracy Breakdown):** Visual percentage bars for Physics, Chemistry, and Maths.

### Group 27: Attendance Record
* **STU-27-01 (🔥 Monthly Attendance Calendar):** Color-coded calendar (Green = Present, Red = Absent, Yellow = Late).
* **STU-27-02 (🔥 Attendance Percentage Meter):** Circular progress meter tracking attendance vs. 75% requirement.
* **STU-27-03 (⚡ Shortage Early-Warning Alert):** Automatic warning when absent days jeopardize exam eligibility.

### Group 28: Academic Performance & Progress
* **STU-28-01 (🔥 Test Score Ledger):** Chronological table of all tests taken, scores, and class ranks.
* **STU-28-02 (🔥 Progress Trend Graph):** Graphical curve showing performance trajectory over the academic year.
* **STU-28-03 (🔥 Downloadable Report Card PDF):** Official formatted term report card with teacher signature line.
* **STU-28-04 (⚡ Batch Percentile Ranking):** Percentile standing relative to all peers in the same batch.

### Group 29: Fee Payment & Billing (Razorpay)
* **STU-29-01 (🔥 Due Fee Amount Card):** Prominent banner displaying overdue balance and upcoming due dates.
* **STU-29-02 (🔥 1-Click Razorpay Checkout):** Pay fees instantly via UPI (GPay, PhonePe, Paytm), Cards, or Netbanking.
* **STU-29-03 (🔥 Instant PDF Receipt Download):** Download official fee payment receipts with transaction IDs.
* **STU-29-04 (🔥 Payment History Statement):** Full historical ledger of all past fee installments paid.

### Group 30: Doubts & Mentorship
* **STU-30-01 (⚡ Ask a Doubt Form):** Submit questions with optional image attachments directly to teachers.
* **STU-30-02 (⚡ Threaded Teacher Responses):** View solutions and follow up on unclear steps.
* **STU-30-03 (⚡ Peer Discussion Board):** Community study group where students discuss tough problems.

### Group 31: Virtual Classroom Access
* **STU-31-01 (⚡ 1-Click "Join Live Lecture" Button):** Direct launcher to Zoom / Google Meet without manual link searching.
* **STU-31-02 (⚡ Lecture Recording Archive):** Rewatch previous classes sorted by chapter and date.
* **STU-31-03 (⚡ Automated Attendance on Entry):** Attendance auto-logged upon joining the session.

### Group 32: Gamification & Recognition
* **STU-32-01 (⚡ Batch Leaderboard):** Top 5 test performers of the week to encourage friendly competition.
* **STU-32-02 (⚡ Attendance Streak Badges):** Earn digital badges for 30 consecutive days of full attendance.
* **STU-32-03 (⚡ Course Completion Certificates):** Automated verifiable completion certificates at term end.
* **STU-32-04 (⚡ Teacher Feedback Form):** Submit anonymous feedback on teaching quality.

### Group 33: Dedicated Parent View
* **STU-33-01 (🔥 Parent Executive Summary):** Clean, uncluttered view for parents highlighting attendance and pending fees.
* **STU-33-02 (🔥 Teacher Remarks & Observations):** View qualitative observations entered by teachers.
* **STU-33-03 (⚡ Parent-Teacher Meeting [PTM] Scheduler):** Book 15-minute consultation slots with faculty.
* **STU-33-04 (🔥 Instant WhatsApp Institute Contact):** 1-tap WhatsApp button to chat with the coaching front desk.

---

## Part 4: Cross-Cutting Architectural Features (7 Groups · 24 Features)

### Group 34: Authentication, Identity & Access Control
* **CRS-34-01 (🔥 Role Switcher Navigation Bar):** Floating top pill to switch between Admin, Teacher, and Student on stage.
* **CRS-34-02 (🔥 Role-Based Access Control [RBAC]):** Secure separation of endpoints based on user roles.
* **CRS-34-03 (⚡ Mobile OTP Authentication):** Passwordless login via SMS OTP verification.
* **CRS-34-04 (⚡ Google OAuth2 Single Sign-On):** 1-click Google account login for students and staff.

### Group 35: Multi-Channel Notification Engine
* **CRS-35-01 (🔥 Instant WhatsApp Lead Trigger):** Direct deep-link message generation for new website admissions.
* **CRS-35-02 (🔥 JavaMailSender SMTP Service):** Asynchronous HTML email confirmations for admissions and fee payments.
* **CRS-35-03 (⚡ Transactional SMS Gateway):** Fallback SMS notifications for urgent attendance and fee alerts.
* **CRS-35-04 (⚡ In-App Push Notifications):** Real-time web and mobile push alerts for schedule changes.

### Group 36: Cross-Platform Mobile Experience
* **CRS-36-01 (🔥 Responsive Mobile App Frame):** Dynamic mobile-optimized viewport mimicking native mobile experience.
* **CRS-36-02 (⚡ React Native Mobile Client):** Cross-platform Android/iOS application codebase.
* **CRS-36-03 (⚡ Offline Local Storage Cache):** Offline access to timetables and downloaded study materials.

### Group 37: Multi-Lingual & Vernacular Support
* **CRS-37-01 (🔥 English + Hinglish Compatibility):** Support for mixed Hindi/English text in pamphlets and builder copy.
* **CRS-37-02 (⚡ Dynamic Locale Switcher):** Toggle between English and Hindi interface text.
* **CRS-37-03 (⚡ Regional Script Generation):** AI copywriter generates marketing copy in regional scripts (Hindi/Marathi).

### Group 38: UI/UX & Design System
* **CRS-38-01 (🔥 Dark / Light Mode Theme Engine):** High-contrast dark theme toggle designed for evening study sessions.
* **CRS-38-02 (⚡ Global Search Bar):** Universal search across students, batches, notes, and fee transactions.
* **CRS-38-03 (🔥 Responsive Adaptive Breakpoints):** Tailored layouts for Mobile (390px), Tablet (768px), and Desktop (1440px).

### Group 39: Cloud Media & Asset Optimization
* **CRS-39-01 (🔥 Client-Side Image Compression):** Downscales uploaded photos to WebP format under 300KB before submission.
* **CRS-39-02 (⚡ Direct-to-Storage Pre-Signed URLs):** Bypasses Spring Boot server for large video and notes uploads.
* **CRS-39-03 (🔥 Time-Limited Signed Document URLs):** Protects PDF study materials from unauthorized hotlinking.

### Group 40: Multi-Tenant SaaS Architecture
* **CRS-40-01 (🔥 Subdomain Routing & Dispatcher):** Dynamically resolves `<slug>.studyflow.in` to the correct tenant.
* **CRS-40-02 (🔥 Tenant Data Isolation):** Logical isolation ensuring Institute A cannot view Institute B's records.
* **CRS-40-03 (🔥 Optimistic Locking Data Integrity):** `@Version` concurrency checks preventing conflicting edits.
* **CRS-40-04 (🔥 Hybrid Seeded Database Engine):** Pre-populated with *"Apex Science Academy"* data + live onboarding.

### Group 41: Multi-Modal Groq AI Pipeline
* **CRS-41-01 (🔥 Vision OCR Extraction [`Qwen 3.8 27B`]):** Extracts structured website data from paper pamphlets in under 1 second.
* **CRS-41-02 (🔥 High-Speed Copywriting [`GPT OSS 20B`]):** Generates compelling headlines, bios, and FAQs in 250 milliseconds.
* **CRS-41-03 (🔥 Voice-to-Website Input [`Whisper Large v3 Turbo`]):** Transcribes spoken teacher audio into structured layout data.


---

## Part 5: Editor Mode Features & Multi-Page Customization Architecture

To provide coaching institutes with Wix-level visual flexibility without technical complexity, StudyFlow implements an **Ordered Block Multi-Page Editor Engine**. Each institute website supports independent sub-pages (`Home`, `About Us`, `Courses & Batches`, `Contact Us`), and educators can customize typography, color schemes, animations, and section ordering with real-time preview synchronization.

```
┌────────────────────────────────────────────────────────────────────────┐
│                   STUDYFLOW VISUAL EDITOR INTERFACE                    │
│                                                                        │
│  [ Top Bar ]: [ Page: Home ▾ ]  [ Desktop | Tablet | Mobile ]  [Save] │
│  ────────────────────────────────────────────────────────────────────  │
│  [ LEFT PANEL: CONTROLS ]          [ RIGHT CANVAS: LIVE PREVIEW ]      │
│  • Page Switcher (Home/About/...)  ┌────────────────────────────────┐  │
│  • Section Reordering (↑ / ↓)      │ Apex Science Academy           │  │
│  • Color Palette & Theming         │ Master JEE Physics (Edit text) │  │
│  • Font Family & Scale (H1-H4)     │ [Book Free Demo Class]         │  │
│  • Animation Selectors (Fade/Slide)│ ------------------------------ │  │
│  • "+ Add New Section" Drawer      │ [Toppers Section]   (↑ / ↓)    │  │
│                                    │ [Batches Section]   (↑ / ↓)    │  │
│                                    └────────────────────────────────┘  │
└────────────────────────────────────────────────────────────────────────┘
```

---

### Multi-Page Storage Schema in MySQL (`layout_data`)

The database stores all pages within an indexed `pages` map. Each page maintains its own independent array of ordered section blocks:

```json
{
  "theme": {
    "primaryColor": "#2563EB",
    "accentColor": "#F59E0B",
    "fontFamily": "Poppins",
    "darkMode": false
  },
  "navigation": [
    { "label": "Home", "path": "/" },
    { "label": "About Us", "path": "/about" },
    { "label": "Courses", "path": "/courses" },
    { "label": "Contact", "path": "/contact" }
  ],
  "pages": {
    "home": {
      "title": "Home",
      "slug": "/",
      "sections": [
        { "id": "sec_hero", "type": "HERO", "enabled": true, "order": 1, "animation": "fade-in", "props": { ... } },
        { "id": "sec_toppers", "type": "TOPPERS", "enabled": true, "order": 2, "animation": "slide-up", "props": { ... } },
        { "id": "sec_batches", "type": "BATCHES", "enabled": true, "order": 3, "animation": "zoom-in", "props": { ... } }
      ]
    },
    "about": {
      "title": "About Us",
      "slug": "/about",
      "sections": [
        { "id": "sec_about_hero", "type": "ABOUT_HERO", "enabled": true, "order": 1, "props": { "title": "18 Years of Kota Legacy" } },
        { "id": "sec_director_msg", "type": "DIRECTOR_MESSAGE", "enabled": true, "order": 2, "props": { ... } },
        { "id": "sec_faculty_grid", "type": "FACULTY_SHOWCASE", "enabled": true, "order": 3, "props": { ... } }
      ]
    },
    "courses": {
      "title": "Courses & Batches",
      "slug": "/courses",
      "sections": [
        { "id": "sec_all_batches", "type": "BATCH_CATALOG", "enabled": true, "order": 1, "props": { ... } },
        { "id": "sec_fees", "type": "FEE_STRUCTURE", "enabled": true, "order": 2, "props": { ... } }
      ]
    },
    "contact": {
      "title": "Contact Us",
      "slug": "/contact",
      "sections": [
        { "id": "sec_map", "type": "GOOGLE_MAPS", "enabled": true, "order": 1, "props": { ... } },
        { "id": "sec_inquiry", "type": "CONTACT_FORM", "enabled": true, "order": 2, "props": { ... } }
      ]
    }
  }
}
```

---

### Complete Website Editor Feature Inventory (35 Features)

#### Group 42: Typography & Text Editing Controls
* **EDT-42-01 (🔥 Inline Canvas Text Editing):** Click directly on any heading, subtitle, or badge in the live preview to edit text in place.
* **EDT-42-02 (🔥 Font Family Selector):** Choose from curated Google Fonts: `Poppins`, `Inter`, `Roboto`, `Montserrat`, and `Outfit`.
* **EDT-42-03 (🔥 Typography Scale Presets):** Apply standard size scales: `H1 (Hero)`, `H2 (Section)`, `H3 (Card)`, `Body`, and `Caption`.
* **EDT-42-04 (🔥 Font Weight Controls):** Select between `Regular (400)`, `Medium (500)`, `Semi-Bold (600)`, and `Bold (700)`.
* **EDT-42-05 (🔥 Text Alignment):** Format text orientation to `Left`, `Center`, or `Right` on any section.
* **EDT-42-06 (🔥 Text Color Picker):** Change font color independently from container background.
* **EDT-42-07 (🔥 AI Copywriting Wand ["✨ Write with AI"]):** 1-click button to polish text, generate headlines, or expand instructor bios via Groq `GPT OSS 20B`.

#### Group 43: Styling, Themes & Color Customization
* **EDT-43-01 (🔥 Global Brand Color Palette):** Customize `Primary Brand Color`, `Secondary Color`, and `Accent / Highlight Color`.
* **EDT-43-02 (🔥 Dark Mode / Light Mode Switcher):** Toggle whole-site aesthetic between Clean White and Dark Slate (`#0F172A`).
* **EDT-43-03 (🔥 Per-Section Background Styler):** Configure solid colors, subtle 5% brand tints, or high-contrast backgrounds per block.
* **EDT-43-04 (🔥 Card & Button Corner Radius):** Slider control for `Sharp (0px)`, `Rounded (8px)`, or `Pill (999px)` borders.

#### Group 44: Layout, Reordering & Section Management
* **EDT-44-01 (🔥 Section Reordering Controls [↑ / ↓]):** Reorder any block up or down on the page via array index swapping.
* **EDT-44-02 (🔥 Section Visibility Toggle [Eye Icon]):** Hide seasonal or unlaunched sections (e.g., Crash Courses) without losing content.
* **EDT-44-03 (🔥 "+ Add New Section" Drawer):** Library modal to insert pre-built modules: `Hero`, `Batches`, `Toppers`, `Fees`, `Faculty`, `Testimonials`, `FAQ`, and `Contact Map`.
* **EDT-44-04 (🔥 Section Vertical Padding Adjuster):** Control spacing between modules: `Compact (32px)`, `Normal (64px)`, or `Spacious (96px)`.
* **EDT-44-05 (🔥 Delete Section with Confirmation):** Remove unwanted sections with safety modal to prevent accidental data loss.

#### Group 45: Media, Images & Document Handling
* **EDT-45-01 (🔥 Image Uploader with Auto-Compression):** Upload photos of teachers, toppers, or classrooms; automatically resized to WebP under 250KB.
* **EDT-45-02 (🔥 Aspect Ratio Lock):** Maintain clean image proportions: `Square (1:1)`, `Landscape (16:9)`, or `Portrait (3:4)`.
* **EDT-45-03 (🔥 Institute Logo Size Slider):** Adjust logo size from `Small (120px)` to `Large (240px)`.
* **EDT-45-04 (🔥 PDF Study Material Linker):** Attach downloadable syllabus or notes PDFs to promotional buttons.
* **EDT-45-05 (⚡ Video Lecture Embedder):** Paste YouTube Unlisted or Vimeo links to showcase video intro lectures.

#### Group 46: Buttons & Call-to-Action (CTA) Actions
* **EDT-46-01 (🔥 Button Text Customizer):** Edit button labels: *"Book Demo Class"*, *"Download Syllabus"*, *"Enroll Now"*.
* **EDT-46-02 (🔥 Multi-Action Button Dispatcher):** Configure button actions:
  * Open demo booking modal
  * Direct WhatsApp chat link (`wa.me/...`)
  * Direct phone dialer (`tel:...`)
  * Smooth scroll to specific page section (e.g., `#batches`)
* **EDT-46-03 (🔥 Button Variant Styles):** Toggle between `Solid Fill`, `Outline Border`, or `Soft Glow`.

#### Group 47: Motion, Animations & Micro-Interactions
* **EDT-47-01 (🔥 Section Scroll Entrance Animation):** Choose entrance effects: `None`, `Fade In`, `Slide Up`, or `Zoom In`.
* **EDT-47-02 (🔥 Interactive Hover Effects):** Cards elevate with shadow (`shadow-lg`) and scale (`scale-105`) on cursor hover.
* **EDT-47-03 (⚡ Announcement Ticker Marquee):** Animated scrolling banner at the top of the site for urgent admissions notices.

#### Group 48: Multi-Device Preview, History & Publishing
* **EDT-48-01 (🔥 Multi-Device Viewport Switcher):** 1-click preview modes:
  * 🖥️ **Desktop View (1440px)**
  * 📱 **Tablet View (768px)**
  * 📲 **Smartphone View (390px)**
* **EDT-48-02 (⚡ Undo / Redo History Stack):** Standard `Ctrl+Z` / `Ctrl+Y` shortcuts to revert editing mistakes.
* **EDT-48-03 (🔥 Instant Live Publish Button):** Commits layout JSON to MySQL and clears CDN edge caches in under 1 second.
* **EDT-48-04 (⚡ High-Res Admission Poster QR Generator):** Download a printable A4 flyer with a QR code linking directly to the published website.

#### Group 49: Dual-Level Sample Data & Preview Simulation
* **EDT-49-01 (🔥 Component-Level Sample Data Toggle):** Floating action badge rendered above the currently selected block. Toggling ON instantly populates the component with standardized realistic mock data (e.g. 3 active batches, faculty badges, topper ranks) so new institutes without live data can preview the layout aesthetic.
* **EDT-49-02 (🔥 Component Auto-Reset on Deselect):** Lifecycle listener that automatically resets the component-level sample toggle to OFF the instant the user clicks outside or focuses another component, keeping the canvas unpolluted.
* **EDT-49-03 (🔥 Page-Level Global Master Sample Preview):** Persistent header switch (`[🧪 Preview with Sample Data]`) located in the editor top bar that simultaneously injects sample placeholder data across all modular components on the active page for full-page aesthetic review.
* **EDT-49-04 (🔥 Publish Safety Tripwire — Auto-Off on Publish):** Pre-publish interceptor that forcibly turns OFF all active sample preview toggles (both component-level and page-level) when the user clicks "Publish Website", ensuring zero mock data is serialized or leaked into the production MySQL database or public domain (`slug.studyflow.in`).

#### Group 50: 3-Tier Site Structure & Dynamic Navigation Engine
* **STR-50-01 (🔥 Default Welcoming Pages Pack):** Every newly created project automatically provisions the 5 core marketing pages (`Home`, `About Us`, `Testimonials`, `Services/Courses`, `Contact Us`) organized in a collapsible editor dropdown.
* **STR-50-02 (🔥 Authentication & Login Structure Dropdown):** Dedicated site structure section allowing institutes to unlock full-stack student/teacher portal access.
* **STR-50-03 (🔥 Unbreakable Auth Auto-Select Bundle):** Selecting either `Login` or `Signup` automatically enables all 3 auth screens (`Login`, `Forgot Password with OTP`, and `Signup`) to guarantee complete authentication loops.
* **STR-50-04 (🔥 Side Navbar Post-Login Router):** Visual configurator for the internal dashboard's persistent left sidebar, displaying active tools, institute branding, and user profile shortcuts.
* **STR-50-05 (🔥 Dual-Destination Feature Activation):** Smart feature router that automatically mounts full tools (Attendance, Notes, Fees, Tests) as distinct links in the Side Navbar, while routing glanceable widgets (Daily Schedule, Fee Alert) to the Dashboard Canvas.

#### Group 51: Visual Editor 3-Pane Flexibility & Dynamic Card Actions
* **EDT-51-01 (🔥 Shopify-Style 3-Pane Editor Layout):** Left panel for page structure & component library, center canvas for real-time WYSIWYG preview, and right panel for contextual styling and properties.
* **EDT-51-02 (🔥 Inline WYSIWYG Click-to-Type):** Click directly on any heading, paragraph, or button label to type edits live on the canvas with 0-delay state synchronization.
* **EDT-51-03 (🔥 Floating Card Action Pill):** Contextual toolbar appearing over any selected card offering `[Edit]`, `[Duplicate]`, `[Style]`, and `[Delete]` actions.
* **EDT-51-04 (🔥 In-Section "+ Add Card" Expansion):** Inline dashed button at the end of collection grids (Batches, Toppers, Teachers, FAQs) to instantiate new cards matching existing styles with 1 click.
* **EDT-51-05 (🔥 "+ Add Element" Atomic Block Drawer):** Insert individual text boxes, metric stat cards, promotional banners, custom buttons, and photo blocks anywhere on the page.

#### Group 52: Dynamic Login Customization & Tenant-Scoped REST API
* **ATH-52-01 (🔥 Flexible Login Identifier Selector):** Choose login credential strategy in the editor inspector: `Student ID / Roll No + Password`, `Mobile Phone + Password`, `Email + Password`, or `Universal Multi-Field`.
* **ATH-52-02 (🔥 Auth Visual Styler):** Switch login layout between `Split-Screen` (Topper/Classroom photo + login card), `Centered Clean Card`, or `Glassmorphism`.
* **ATH-52-03 (🔥 Universal Dynamic Login Request DTO):** Client sends unified `{ "identifier": "...", "password": "..." }` payload accepting roll IDs, emails, or phone numbers seamlessly.
* **ATH-52-04 (🔥 Tenant-Scoped Public Auth Gateway):** Backend endpoint `POST /api/public/{slug}/auth/login` resolving the tenant via `{slug}`, querying users within `project_id`, verifying BCrypt hashes, and issuing scoped JWTs without cross-tenant collisions.

#### Group 53: Universal Component Motion & Animation Engine (Wix Studio-Grade)
* **MOT-53-01 (🔥 Floating Action Pill [⚡ Motion] Trigger):** Clicking any component or card displays a quick-action floating pill featuring the `[⚡ Motion]` button, opening the motion customization modal.
* **MOT-53-02 (🔥 Comprehensive 4-Category Presets Library):** Includes 55+ curated presets across 4 distinct motion categories:
  * **Entrance (17 presets):** `Fade`, `Shutters`, `Fold`, `Blur`, `Turn`, `Expand`, `Shrink`, `Tilt`, `Reveal`, `Shape`, `Arc`, `Flip`, `Grow`, `Wink`, `Slide`, `Spin`, `Bounce`.
  * **Loop (13 presets):** `Breathe`, `Rubber`, `Flip`, `Poke`, `Fold`, `Pulse`, `Cross`, `Wiggle`, `Bounce`, `Spin`, `Jello`, `Flash`, `Swing`.
  * **Scroll (17 presets):** `Turn`, `Tilt`, `Parallax`, `Fade`, `Dart`, `Grow`, `Shrink`, `3D Spin`, `Blur`, `Slant`, `Shutters`, `Stretch`, `Reveal`, `Spin`, `Slide`, `Shape`, `Arc`.
  * **Mouse Effects (8 presets):** `Tilt 3D`, `Track`, `Blob`, `Blur`, `Track 3D`, `Airy`, `Resize`, `Skew 3D`.
* **MOT-53-03 (🔥 Granular Sub-Element Target Selector):** Dropdown allowing users to target motion either to the `Entire Container (self)`, or specifically to the `H1 Heading`, `P Text`, `Button (CTA)`, `Stack`, or `Vector Art / Icon`.
* **MOT-53-04 (🔥 1-Click Interactive Canvas Live Preview & Replay):** Clicking any animation tile instantly triggers a live preview execution on that exact canvas element with 0ms lag, accompanied by a `[🔄 Replay Preview]` button.
* **MOT-53-05 (🔥 Right Inspector Motion Settings):** Persistent panel controls allowing fine-tuning of `Duration` slider (0.1s to 3.0s), `Delay` slider (0.0s to 2.0s), Direction toggle (`[In] / [Out]`), and a 1-click `[❌ Remove Animation]` action.
* **MOT-53-06 (🔥 Universal <MotionWrapper> Higher-Order Component):** Reusable React Framer Motion wrapper providing 60 FPS hardware-accelerated animations across any block without custom CSS bloat.
* **MOT-53-07 (🔥 Interactive Mouse & Cursor Effects Engine):** Real-time cursor-tracking physics engine computing mathematical tilt vectors ($\text{rotateX}, \text{rotateY}$) and magnetic translations based on mouse position relative to the element bounding box, supporting `Tilt 3D`, `Track`, `Blob`, `Blur`, `Track 3D`, `Airy`, `Resize`, and `Skew 3D`.

#### Group 54: Visual Editor Structural Layers & DOM Tree Hierarchy Engine (Wix Studio-Grade)
* **LAY-54-01 (🔥 Expandable Hierarchical Layers Tree):** Left sidebar tree navigation mirroring the exact DOM and JSON layout structure across pages, sections, containers, and atomic elements.
* **LAY-54-02 (🔥 Two-Way Bidirectional Synchronization):** Selecting an element on the canvas instantly expands and highlights its row in the layers tree; clicking any layer smoothly scrolls the canvas into focus and triggers its floating action pill.
* **LAY-54-03 (🔥 Eye Icon Instant Visibility & Seasonality Toggle):** Toggle element visibility (`hidden: boolean`) without deleting it. In editor, shows with diagonal hatch; on live published site, completely excluded (`display: none`).
* **LAY-54-04 (🔥 Padlock Accidental Edit & Drag Shield):** Lock components (`locked: boolean`) to protect foundational branding, grids, and institute logos from unintended edits by staff or teachers.
* **LAY-54-05 (🔥 Visual Drag-and-Drop Reordering & Z-Index Stacking):** Reorder sibling elements to rearrange flex/grid content flow and update absolute element `zIndex` stacking order.
* **LAY-54-06 (🔥 In-Place Double-Click Layer Renaming):** Inline edit layer names to assign custom business identifiers (e.g. "WhatsApp Demo CTA", "AIR 1 Topper Badge").
* **LAY-54-07 (🔥 Right-Click Context Menu & Motion Quick-Launch):** Contextual shortcut menu on any layer for 1-click `[⚡ Motion]`, `[📋 Duplicate]`, `[🔼 Bring to Front]`, `[🔽 Send to Back]`, and `[🗑️ Delete]`.

#### Group 55: Aceternity UI High-Aesthetic Component Architecture
* **ACE-55-01 (🔥 Hero Section Spotlight & Aurora Backgrounds):** Ambient dynamic illumination effects reacting to cursor hover, delivering modern, dark/light aesthetic backdrops for institute hero showcases.
* **ACE-55-02 (🔥 3D Perspective Tilt & Hover Cards):** Multi-layer interactive course and batch cards with 3D depth, parallax layer elevation, and glowing borders on cursor hover.
* **ACE-55-03 (🔥 Infinite Moving Testimonials & Topper Ticker):** Smooth, velocity-controlled infinite marquee carousel showcasing verified student reviews and national exam rank results.
* **ACE-55-04 (🔥 Bento Grid Modular Feature Showcase):** Asymmetric Apple/Vercel-style grid layout highlighting coaching USPs (Smart Classrooms, Study Materials, Mock Tests, Doubt Labs).
* **ACE-55-05 (🔥 Dynamic Text Flip & Typewriter Headlines):** Animated rotating keywords in headlines (*"Crack [JEE | NEET | CUET | Boards] with India's Top Mentors"*).
* **ACE-55-06 (🔥 Glowing Moving-Border CTA Buttons):** Eye-catching gradient light border circulating around primary conversion buttons (*"Enroll Now"*, *"Book Free 3-Day Demo"*).





