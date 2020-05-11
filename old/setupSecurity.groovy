import jenkins.model.*
import hudson.security.*

def instance = Jenkins.getInstance()

def hudsonRealm = new HudsonPrivateSecurityRealm(false)
hudsonRealm.createAccount("admin","admin")
instance.setSecurityRealm(hudsonRealm)
instance.setInstallState(InstallState.INITIAL_SETUP_COMPLETED)
instance.save()
