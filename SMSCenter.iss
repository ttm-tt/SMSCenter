; Script generated by the Inno Setup Script Wizard.
; SEE THE DOCUMENTATION FOR DETAILS ON CREATING INNO SETUP SCRIPT FILES!

[Setup]
AppName=SMSCenter
AppVerName=SMSCenter 22.06.01
AppPublisher=Christoph Theis
DefaultDirName={autopf}\TTM
DefaultGroupName=TTM
OutputDir=.\Output
OutputBaseFilename=install
MinVersion= 0,6.1

; Sign installer
; SignTool=MS /d $qTTM Installer$q $f


[Types]
Name: "client"; Description: "SMS Center for TTM"

[Components]
Name: "Client"; Description: "SMS Center for TTM"; Types: client; Flags: fixed


[Tasks]
Name: "desktopicon"; Description: "Create a &desktop icon"; GroupDescription: "Additional icons:";

[Files]
Source: ".\dist\SMSCenter.jar"; DestDir: "{app}"; Flags: ignoreversion
Source: ".\dist\*.dll"; DestDir: "{app}"; Flags: ignoreversion
Source: ".\dist\lib\*.jar"; DestDir: "{app}\lib"; Flags: ignoreversion
Source: ".\lib\rxtxSerial.dll"; DestDir: "{app}"; Flags: ignoreversion
Source: ".\dist\SMSCenter.ico"; DestDir: "{app}"; Flags: ignoreversion 
Source: "..\TTM Manuals\src\SMSCenter\SMSCenter.pdf"; DestDir: "{app}"; Flags: ignoreversion
Source: ".\SMSServer.template.conf"; DestDir: "{commonappdata}\TTM"; DestName: "SMSServer.conf"; Flags: onlyifdoesntexist uninsneveruninstall
Source: ".\3rdparty.html"; DestDir: "{app}"; Flags: ignoreversion

[Registry]
; HKLM\Software\JavaSoft\Prefs should be created by JRE installer, but that does not always happen
Root: HKLM; Subkey: "Software\JavaSoft\Prefs"; Flags: noerror

[Icons]
Name: "{group}\SMSCenter"; Filename: "{app}\SMSCenter.jar"; WorkingDir: "{app}";
Name: "{userdesktop}\SMSCenter"; Filename: "{app}\SMSCenter.jar"; WorkingDir: "{app}"; Tasks: desktopicon; IconFilename: "{app}\SMSCenter.ico";

[Code]
(* looks for JDK or JRE version in Registry *)
function getJREVersion(): String;
var
	jreVersion: String;
begin
	jreVersion := '';
  if IsWin64 then begin
	  RegQueryStringValue(HKLM64, 'SOFTWARE\JavaSoft\JRE', 'CurrentVersion', jreVersion);
  end
  else begin
  	RegQueryStringValue(HKLM, 'SOFTWARE\JavaSoft\JRE', 'CurrentVersion', jreVersion);
  end;
	Result := jreVersion;
end;

(* looks for JDK version, in Registry *)
function getJDKVersion(): String;
var
	jdkVersion: String;
begin
	jdkVersion := '';
  if IsWin64 then begin
	  RegQueryStringValue(HKLM64, 'SOFTWARE\JavaSoft\JDK', 'CurrentVersion', jdkVersion);
  end
  else begin
  	RegQueryStringValue(HKLM, 'SOFTWARE\JavaSoft\JDK', 'CurrentVersion', jdkVersion);
  end;
	Result := jdkVersion;
end;

(* Finds path to "javaw.exe" by looking up JDK or JRE locations *)
(* in the registry.  Ensures the file actually exists.  If none *)
(* is found, an empty string is returned. 						          *)
function GetJavaPath(Default: String): String;
var
	javaVersion: String;
	javaHome: String;
	path: String;
begin
	path := '';
	javaVersion := getJDKVersion();
	if (Length(javaVersion) > 0) and (javaVersion >= '11') then begin
    if IsWin64 then begin
  		RegQueryStringValue(HKLM64, 'SOFTWARE\JavaSoft\JDK\' + javaVersion, 'JavaHome', javaHome)
    end 
    else begin
  		RegQueryStringValue(HKLM, 'SOFTWARE\JavaSoft\JDK\' + javaVersion, 'JavaHome', javaHome)
    end;
    if javaHome <> '' then begin
		  path := javaHome + '\bin\' + 'javaw.exe';
		  if not FileExists(path) then begin
        path := '';
		  end;
    end;
	end;
  (* if we didn't find a JDK "javaw.exe", try for a JRE one *)
	if Length(path) = 0 then begin
		javaVersion := getJREVersion();
	  if (Length(javaVersion) > 0) and ((javaVersion) >= '11') then begin
      if IsWin64 then begin
	      RegQueryStringValue(HKLM64, 'SOFTWARE\JavaSoft\Java Runtime Environment\' + javaVersion, 'JavaHome', javaHome) 
      end
      else begin
	      RegQueryStringValue(HKLM, 'SOFTWARE\JavaSoft\Java Runtime Environment\' + javaVersion, 'JavaHome', javaHome) 
      end;
      if javaHome <> '' then begin
        path := javaHome + '\bin\' + 'javaw.exe';
	      if not FileExists(path) then begin
	        path := '';
	      end;
      end;
    end;
  end;
  Result := path;
end;

(* Called on setup startup *)
function InitializeSetup(): Boolean;
var
	javaPath: String;
begin
	javaPath := GetJavaPath('');
	if Length(javaPath) > 0 then begin
		(* MsgBox('Found javaw.exe here: ' + javaPath, mbInformation, MB_OK); *)
		Result := true;
	end
	else begin
		MsgBox('Setup is unable to find a Java Development Kit or Java Runtime 8, or higher, installed.' + #13 +
			     'You must have installed at least JDK or JRE, 7 or higher to continue setup.' + #13 +
           'Please install one from http://java.sun.com and then run this setup again.', mbInformation, MB_OK);
		Result := true;
	end;
end;





