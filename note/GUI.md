<center>

# GUI

Yulin XIE

</center>



#### Containers
- `JFrame` has minimize and maximize option.

- `JDialog` do not support minimize and maximize option.

- If  `JFrame jf = new JFrame();` &emsp; then &emsp; `JDialog jd = new JDialog(jf);` &emsp; then &emsp;  `jd.setModel(true);`, we cannot activate the father window if there is a son window at front.

- For a JFrame we do `jf.setResizable(false);` then the window is not resizable any more.

-
