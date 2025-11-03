# Privacy Policy - HabitHub

**Last Updated: November 3, 2025**

## Overview

HabitHub is a privacy-first Android application designed for monthly objectives management. This privacy policy explains our data practices and your rights regarding personal information.

## Data Collection Statement

**HabitHub collects zero data.** The application does not collect, store, transmit, or process any personal information on external servers.

## Local Data Storage

HabitHub stores the following information exclusively on your device:

- **Monthly Objectives**: Titles and descriptions of your goals
- **Daily Notes**: Calendar notes you create
- **Objective Completions**: Records of completed objectives
- **User Preferences**: Language selection (Spanish/English) and theme settings (OLED)

All data is stored locally using:

- **Room Database** (SQLite): For structured data (objectives, notes, completions)
- **DataStore**: For user preferences (theme, language)

These storage mechanisms are isolated within the application's private directory (`/data/data/com.dennnisver4.habithub/`), protected by Android's security model.

## What We Do Not Do

HabitHub explicitly does not:

- **Network Connectivity**: Does not require or use internet access
- **Analytics & Telemetry**: No tracking, analytics, or usage statistics collection
- **Advertising**: Completely ad-free
- **User Accounts**: No registration, authentication, or cloud accounts
- **Permissions**: Does not request location, contacts, camera, or other dangerous permissions
- **Cloud Synchronization**: No data synchronization with Google Drive or any cloud service
- **Data Sharing**: No data sharing with third parties (we have no data to share)
- **Cookies & Tracking**: No cookies, web beacons, or tracking technologies

## Security Measures

HabitHub implements multiple security layers to protect your data:

### Technical Safeguards

1. **Android Sandbox Isolation**: The application operates within Android's security sandbox, preventing unauthorized access from other applications
2. **Network Security Configuration**: Network access is completely blocked through Android's Network Security Config
3. **Backup Disabled**: Automatic backups to cloud services (Google Drive) are explicitly disabled
4. **Code Obfuscation**: Production builds use ProGuard for code obfuscation, protecting against reverse engineering
5. **Private Storage**: All data stored in application-private directories with filesystem-level protection

### Privacy Architecture

```text
┌─────────────────────────────────────┐
│         Your Device                 │
│  ┌───────────────────────────────┐  │
│  │       HabitHub App            │  │
│  │  ┌─────────────────────────┐  │  │
│  │  │  Room Database (Local)  │  │  │
│  │  │  - Objectives           │  │  │
│  │  │  - Notes                │  │  │
│  │  │  - Completions          │  │  │
│  │  └─────────────────────────┘  │  │
│  │  ┌─────────────────────────┐  │  │
│  │  │  DataStore (Local)      │  │  │
│  │  │  - Theme Preferences    │  │  │
│  │  │  - Language             │  │  │
│  │  └─────────────────────────┘  │  │
│  └───────────────────────────────┘  │
└─────────────────────────────────────┘
         ❌ NO NETWORK CONNECTION
         ❌ NO INTERNET ACCESS
         ❌ NO EXTERNAL SERVERS
```

## Data Storage Location

Application data is stored in the following private directories:

```text
/data/data/com.dennnisver4.habithub/
├── databases/
│   └── habit_hub_database    # SQLite database
└── files/
    └── datastore/
        └── theme_preferences # Theme preferences
```

These directories are protected by Android's permission model and are inaccessible to other applications or external processes.

## Data Deletion

You have complete control over your data and can delete it at any time through two methods:

### Method 1: Uninstall Application

Navigate to: Settings > Apps > HabitHub > Uninstall

This action permanently removes the application and all associated data from your device.

### Method 2: Clear Application Data

Navigate to: Settings > Apps > HabitHub > Storage > Clear Data

This action deletes all data while preserving the application installation.

## Device Transfer

When migrating to a new device, your data will not transfer automatically due to:

- Disabled cloud backups
- No synchronization mechanisms
- Local-only data storage

Data remains exclusively on the original device. Future versions may include manual export/import functionality for local data migration (without internet connectivity).

## User Rights

You maintain full control over your data with the following rights:

- **Right of Access**: All data resides on your device and is accessible at any time through the application interface
- **Right of Rectification**: Modify objectives and notes through the application's editing features
- **Right of Erasure**: Complete data deletion via application uninstallation or data clearing
- **Right of Portability**: (Planned feature) Export data in JSON format for migration

## Legal Compliance

HabitHub complies with major privacy regulations:

- **GDPR** (General Data Protection Regulation - European Union)
- **CCPA** (California Consumer Privacy Act - United States)
- **LGPD** (Lei Geral de Proteção de Dados - Brazil)
- **PIPEDA** (Personal Information Protection and Electronic Documents Act - Canada)

By design, the application does not collect, process, or transmit personal data, eliminating data breach risks and simplifying regulatory compliance.

## Android Permissions

HabitHub requests zero Android permissions. The application does not declare or request:

- Internet access
- Network state access
- Location access (fine or coarse)
- Camera access
- Contacts access
- External storage access
- Phone state access
- Microphone access
- Background location
- Activity recognition

## Open Source Transparency

HabitHub is open source software distributed under the MIT License:

- **Repository**: [github.com/dennnisver4/HabitHub](https://github.com/dennnisver4/HabitHub)
- **Audit**: Source code is publicly available for independent security audits
- **Contribution**: Community contributions are welcome to enhance privacy and security

The open-source nature allows independent verification of our privacy claims.

## Contact Information

For privacy-related inquiries:

- **Email**: [dennnisver4@gmail.com](mailto:dennnisver4@gmail.com)
- **GitHub Issues**: [github.com/dennnisver4/HabitHub/issues](https://github.com/dennnisver4/HabitHub/issues)

## Policy Updates

This privacy policy may be updated to reflect application changes or regulatory requirements. Updates will be posted with a revised date. Given the zero-data-collection architecture, future updates will maintain the same privacy-first commitment.

---

## Executive Summary

HabitHub implements privacy-by-design principles:

1. **100% Offline Operation**: No internet connectivity required or utilized
2. **Zero Data Collection**: No personal information collected, stored externally, or transmitted
3. **Local-Only Storage**: All data remains on user's device
4. **No Cloud Backups**: Cloud synchronization explicitly disabled
5. **No Tracking**: No analytics, telemetry, or usage tracking
6. **Ad-Free**: No advertising networks or third-party SDKs
7. **Open Source**: Publicly auditable codebase
8. **Regulatory Compliance**: GDPR, CCPA, LGPD, PIPEDA compliant by design

**Your privacy is guaranteed. Your data belongs exclusively to you.**

---

## Appendix: Technical Specifications

### Network Security Configuration

```xml
<network-security-config>
    <base-config cleartextTrafficPermitted="false">
        <trust-anchors>
            <!-- Empty trust anchors - blocks all network traffic -->
        </trust-anchors>
    </base-config>
</network-security-config>
```

### Manifest Configuration

```xml
<application
    android:allowBackup="false"
    android:fullBackupContent="false"
    android:usesCleartextTraffic="false"
    android:networkSecurityConfig="@xml/network_security_config"
    android:hasFragileUserData="true">
```

### Data Extraction Rules

All backup domains explicitly excluded:

- Root domain
- Files domain
- Database domain
- Shared preferences domain
- External storage domain

---

*This privacy policy is written for clarity and transparency. For questions or clarifications, please contact us using the information provided above.*
