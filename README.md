# Project Report: StarWars - Escaping the Galaxy


Team members: Maddy Scott, Justus DeLoach, Louis Tao


Project Description:
    A 2D StarWars–themed platformer game where the player chooses a team of character and navigates through a tile-based level with platforms, hazards, and movement physics.


Technical Guide:
    Requirements:
	    •	Java 17 or 21
	    •	Kilt Graphics library (included in Macalester COMP127 setup)
	    •	Image assets stored in the res/ directory

    How to Run:
        1.	Run Sketch.java.
	    2.	Click “Continue” to proceed to the character selection screen.
	    3.	Choose your preferred character combination.
	    4.	Click “Start Game” to launch the platformer level.
        You may also enter sub-screens directly by running StartScreen.java or CharacterScreen.java, but the primary entry point is Sketch.java.

    Acknowledgements:
	    •	Art assets created by the team or sourced from publicly available Star Wars fan-art and sprite resources, used only for educational and non-commercial purposes.
	    •	The project was built with the Kilt Graphics Java library from Macalester College.
	    •	Gameplay design inspiration comes from classic platformers such as the water girl and the fire boy.


Known Issues
	Accessibility limitations:
	•   Game currently requires keyboard input and visual feedback; inaccessible to blind/low-vision users or those relying on assistive devices.
    
	Collision detection limitations:
	•	Occasional jitter or snapping when the player interacts with tile edges due to AABB collision simplification.

	Fixed window resolution:
	•	The window size is static and does not adapt to different monitors.
    
	No audio cues:
	•	Players with limited visual tracking may find the game harder to play.


Societal Impact Statement:
    Although this is a small student project, all software carries social impact. We considered several ethical dimensions:

    Accessibility & Inclusivity:
	    •	The game currently excludes players who cannot use a keyboard or who rely on screen readers.
	    •	No alternative input modes, captions, high-contrast modes, or visual-impairment accommodations are provided.
        (Recognizing these gaps helps us understand how even simple programs can unintentionally marginalize certain users.)

    Cultural Representation:
	    •	The Star Wars theme may not be universally familiar or meaningful; however, we ensured the content remained non-violent and approachable for general audiences.
	    •	No harmful stereotypes or exclusionary character portrayals were included.

    Unintended Consequences:
	    •	The software is offline and low-risk for malicious misuse, but the design patterns (e.g., inaccessible UI) could propagate if reused without reflection.

    Reflection:
        This project helped us practice socially responsible computing by identifying who is unintentionally excluded and how design decisions embed values into software. Even small games are not value-neutral, and understanding this early is important for our development as ethical programmers.
