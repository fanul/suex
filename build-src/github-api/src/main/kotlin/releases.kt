import json.RepoRelease
import json.RepoReleaseData
import kotlinx.serialization.json.Json
import java.net.URL

@Suppress("MemberVisibilityCanBePrivate", "CanBeParameter", "unused")
class GitHubReleasesHandler(val owner: String, val repo: String) {
    val releasesURL = URL("""https://api.github.com/repos/${owner}/${repo}/releases""")
    val latestURL = URL("""https://api.github.com/repos/${owner}/${repo}/releases/latest""")

    private val json = Json {
        ignoreUnknownKeys = true
    }

    val latestRelease: RepoRelease by lazy {
        json.decodeFromString(String(latestURL.readBytes()))
    }
    val latestReleaseData: RepoReleaseData by lazy {
        json.decodeFromString(String(URL(latestRelease.url).readBytes()))
    }

    val releases: List<RepoRelease> by lazy {
        json.decodeFromString(String(releasesURL.readBytes()))
    }
    val releasesData: Map<RepoRelease, Lazy<RepoReleaseData>> by lazy {
        releases.associateWith { release ->
            lazy {
                json.decodeFromString(String(URL(release.url).readBytes()))
            }
        }
    }
}
